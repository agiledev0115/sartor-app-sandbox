package com.sartor.ui.fragments.discovery_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.response.BrandResponse
import com.sartor.data.model.Brands
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import javax.inject.Inject

@HiltViewModel
class DiscoveryViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _listOfBrands = MutableLiveData<List<Brands>>()
    val listOfBrands : LiveData<List<Brands>> = _listOfBrands

//    private val _listOfSellers = MutableLiveData<BrandResponse>()
//    val listOfSellers : LiveData<BlogResponse> = _listOfSellers


    fun getBrandList(){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getBrands(object : OperationCallback<List<BrandResponse>>{
                override fun onSuccess(data: List<BrandResponse>?) {
                    _isViewLoading.postValue(false)
                    data!!.forEach {
                        _listOfBrands.postValue(
                            immutableListOf(
                                Brands(
                                    it.followers,
                                    it.likes,
                                    it.isTop,
                                    it.img,
                                    it.title,
                                    it.id
                                )
                            )
                        )
                    }
                }
                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }
            })
        }
    }


//
//    fun getBlog(id : String ){
//        _isViewLoading.value = true
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            repositoryImpl.getBlog(id, object: OperationCallback<BlogResponse>{
//                override fun onSuccess(data: BlogResponse?) {
//                    _isViewLoading.postValue(false)
//                    _blog.postValue(data!! )
//                }
//
//                override fun onError(error: String?) {
//                    _onMessageError.postValue(error!!)
//                    _isViewLoading.postValue(false)
//                }
//
//            })
//        }
//    }



}