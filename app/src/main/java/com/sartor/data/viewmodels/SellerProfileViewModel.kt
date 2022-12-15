package com.sartor.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerProfileViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel(){


    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessage = MutableLiveData<Any>()
    val onMessage: LiveData<Any> = _onMessage



}