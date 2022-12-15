package com.sartor.data.api

import android.content.Context
import com.sartor.data.db.SharedPreference
import okhttp3.Interceptor
import okhttp3.Response



class AuthInterceptor(context: Context) : Interceptor {
    private val sharedPreference = SharedPreference(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sharedPreference.getToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}
