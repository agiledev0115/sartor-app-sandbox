package com.sartor.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _listOfProducts = MutableLiveData<List<ProductResponse>>()
    val listOfProducts : LiveData<List<ProductResponse>> = _listOfProducts

    private val _product = MutableLiveData<ProductResponse>()
    val product : LiveData<ProductResponse> = _product



    fun getProductList(){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getProducts(object : OperationCallback<List<ProductResponse>> {
                override fun onSuccess(data: List<ProductResponse>?) {
                    _isViewLoading.postValue(false)
                    _listOfProducts.postValue(data!! )
                }
                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }
            })
        }
    }


    fun getProduct(id : String ){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getProduct(id, object: OperationCallback<ProductResponse>{
                override fun onSuccess(data: ProductResponse?) {
                    _isViewLoading.postValue(false)
                    _product.postValue(data!! )
                }

                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }

            })
        }
    }







}