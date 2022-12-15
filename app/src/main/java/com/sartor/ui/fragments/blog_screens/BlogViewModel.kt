package com.sartor.ui.fragments.blog_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.SignUpRequest
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.SignUpResponse
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _listOfBlogs = MutableLiveData<List<BlogResponse>>()
    val listOfBlogs : LiveData<List<BlogResponse>> = _listOfBlogs

    private val _blog = MutableLiveData<BlogResponse>()
    val blog : LiveData<BlogResponse> = _blog


    fun getBlogList(){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getAllBlogs(object : OperationCallback<List<BlogResponse>>{
                override fun onSuccess(data: List<BlogResponse>?) {
                    _isViewLoading.postValue(false)
                    _listOfBlogs.postValue(data!! )
                }
                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }
            })
        }
    }



    fun getBlog(id : String ){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getBlog(id, object: OperationCallback<BlogResponse>{
                override fun onSuccess(data: BlogResponse?) {
                    _isViewLoading.postValue(false)
                    _blog.postValue(data!! )
                }

                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                }

            })
        }
    }



}