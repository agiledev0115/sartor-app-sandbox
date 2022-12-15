package com.sartor.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.AddToCartRequest
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.CartResponse
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDescriptionViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) :ViewModel() {


    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessage = MutableLiveData<Any>()
    val onMessage: LiveData<Any> = _onMessage


    fun addToCart(id : String ){

        _isViewLoading.value = true


        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.addToCart(AddToCartRequest(id), object: OperationCallback<CartResponse> {
                override fun onSuccess(data: CartResponse?) {
                    _isViewLoading.postValue(false)
                    _onMessage.postValue("Added to cart " )
                    _isSuccessful.postValue(true)
                }

                override fun onError(error: String?) {
                    _onMessage.postValue(error!!)
                    _isViewLoading.postValue(false)
                    _isSuccessful.postValue(false)
                }

            })
        }
    }








}