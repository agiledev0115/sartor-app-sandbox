package com.sartor.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.LoginRequest
import com.sartor.data.api.response.LoginResponse
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel()  {

    private val _isLogoutSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLogoutSuccessful

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError


    fun logoutUser() {
        _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch{
            repositoryImpl.logOut()
            _isLogoutSuccessful.postValue(true)
        }

    }


}