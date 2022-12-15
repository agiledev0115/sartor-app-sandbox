package com.sartor.data.repositories

import com.sartor.data.api.ApiService
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.AddToCartRequest
import com.sartor.data.api.request.CheckoutRequest
import com.sartor.data.api.request.LoginRequest
import com.sartor.data.api.request.SignUpRequest
import com.sartor.data.api.response.*
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Cart
import com.sartor.data.model.CartItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreference: SharedPreference
) : RemoteDataSource {


    override suspend fun loginUser(
        loginRequest: LoginRequest,
        callback: OperationCallback<LoginResponse>
    ) {
        val response = apiService.loginUser(loginRequest)
        if (response.isSuccessful) {
            sharedPreference.saveUserData(response.body()!!, isUserLogin = true)
            callback.onSuccess(response.body())
        } else {
            callback.onError("Something happened")
        }

    }

    override suspend fun registerUser(
        signUpRequest: SignUpRequest,
        callback: OperationCallback<SignUpResponse>
    ) {
        val response = apiService.createUser(signUpRequest)

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }
    }

    override suspend fun getAllBlogs(callback: OperationCallback<List<BlogResponse>>) {
        val response = apiService.getAllBlogs()

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }
    }


    override suspend fun getBlog(id: String, callback: OperationCallback<BlogResponse>) {
        val response = apiService.getBlog(id)

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }
    }



    /*suspend fun getBrand(id: String, callback: OperationCallback<BrandResponse>) {
         val response = apiService.getSpecificBrand(id)

         if (!response.isSuccessful){
             callback.onError(response.errorBody().toString())
         }else  {
             callback.onSuccess(response.body())
         }
     }*/

    override suspend fun getProducts(callback: OperationCallback<List<ProductResponse>>) {
        val response = apiService.getProductList()

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }
    }

    override suspend fun getProduct(
        id: String,
        callback: OperationCallback<ProductResponse>
    ) {

        val response = apiService.getProduct(id)

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }


    }

    override suspend fun addToCart(
        addToCartRequest: AddToCartRequest,
        callback: OperationCallback<CartResponse>
    ) {
        val response = apiService.addToCart(addToCartRequest)

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }


    }

    override suspend fun getCart(callback: OperationCallback<CartResponse>) {
        val response = apiService.getCartList()

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }

    }

    override suspend fun getBrands(callback: OperationCallback<List<BrandResponse>>) {
        val response = apiService.getAllBrands()

        if (!response.isSuccessful) {
            callback.onError(response.errorBody().toString())
        } else {
            callback.onSuccess(response.body())
        }

    }



    override suspend fun makePayment(
        checkoutRequest: CheckoutRequest,
        callback: OperationCallback<CheckoutResponse>
    ) {
        val response = apiService.startCheckout(checkoutRequest)

        if (!response.isSuccessful) {
            callback.onError("Your card number is incorrect ")
        } else if (response.code() == 402) {
            callback.onError("Your card number is incorrect.")
        } else {
            callback.onSuccess(response.body())
        }


    }

    override suspend fun logOut() {
        sharedPreference.logoutUser()
    }


}


