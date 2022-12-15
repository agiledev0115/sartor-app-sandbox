package com.sartor.ui.fragments.brand_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.response.BrandResponse
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _listOfBrands = MutableLiveData<List<BrandResponse>>()
    val listOfBrands : LiveData<List<BrandResponse>> = _listOfBrands

    private val _brand = MutableLiveData<BrandResponse>()
    val brand : LiveData<BrandResponse> = _brand


    fun getBrandList(){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getBrands(object : OperationCallback<List<BrandResponse>>{
                override fun onSuccess(data: List<BrandResponse>?) {
                    _isViewLoading.postValue(false)
                    _listOfBrands.postValue(data!! )
                }
                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }
            })
        }
    }






}