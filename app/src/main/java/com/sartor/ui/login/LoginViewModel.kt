package com.sartor.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.LoginRequest
import com.sartor.data.api.response.LoginResponse
import com.sartor.data.repositories.RepositoryImpl
import com.sartor.util.constants.NetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl)
    : ViewModel() {


    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError


    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadMuseums()" on constructor. Also, if you rotate the screen, the service will not be called.
    init {
        //loadMuseums()
    }
     */

    fun loginUser(loginRequest: LoginRequest) {
        _isViewLoading.value = true

        viewModelScope.launch{
        repositoryImpl.loginUser(loginRequest,object : OperationCallback<LoginResponse> {
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error!!)
                _isLoginSuccessful.postValue(false)
            }

            override fun onSuccess(data: LoginResponse?) {
                _isViewLoading.postValue(false)
                _isLoginSuccessful.postValue(true)
            }


        })
        }

    }








}