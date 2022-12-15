package com.sartor.di

import android.content.Context
import com.google.gson.Gson
import com.sartor.BuildConfig
import com.sartor.data.api.ApiService
import com.sartor.data.api.AuthInterceptor
import com.sartor.data.db.SharedPreference
import com.sartor.data.repositories.RemoteDataSource
import com.sartor.data.repositories.Repository
import com.sartor.data.repositories.RepositoryImpl
import com.sartor.util.constants.NetworkAvailable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {






    /*Networking*/

    private val CONNECTION_TIMEOUT = 500L
    private  val BASE_URL = "http://52.159.111.122:8082"
    private val PREFS_NAME = "com.sartor.app"

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG){
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }else{
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.NONE
            }
        }

    }

    @Provides
    @Singleton
    fun providesAuthenticationToken(@ApplicationContext context: Context) : Interceptor{
        return AuthInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
        loggingInterceptor: HttpLoggingInterceptor

    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(context))
            //.readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            // .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(BASE_URL)
            .build()
    }




    @Provides
    @Singleton
    fun provideApiService( retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalPreference(@ApplicationContext context: Context) : SharedPreference = SharedPreference(context)

    @Singleton
    @Provides
    fun provideRemoteRepositoryImpl(apiService: ApiService, sharedPreference: SharedPreference) :
            RemoteDataSource = RepositoryImpl(apiService,sharedPreference)




    @Singleton
    @Provides
    fun provideRepository(repositoryImpl: RepositoryImpl) = Repository(repositoryImpl)




}