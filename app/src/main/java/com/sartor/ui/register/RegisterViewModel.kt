package com.sartor.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.SignUpRequest
import com.sartor.data.api.response.LoginResponse
import com.sartor.data.api.response.SignUpResponse
import com.sartor.data.repositories.Repository
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sign
@HiltViewModel
class RegisterViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isRegisterSuccessful = MutableLiveData<Boolean>()
    val isRegisterSuccessful : LiveData<Boolean> = _isRegisterSuccessful



    fun registerUser(signUpRequest: SignUpRequest){
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.registerUser(signUpRequest,object : OperationCallback<SignUpResponse>{
                override fun onSuccess(data: SignUpResponse?) {
                    _isViewLoading.postValue(false)
                    _isRegisterSuccessful.postValue(true )
                }

                override fun onError(error: String?) {
                    _onMessageError.postValue(error!!)
                    _isViewLoading.postValue(false)
                    _isRegisterSuccessful.postValue(false )
                }

            })
        }

    }

}