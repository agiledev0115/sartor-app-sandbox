package com.sartor.ui.fragments.payment_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.CheckoutRequest
import com.sartor.data.api.response.CheckoutResponse
import com.sartor.data.model.CheckoutBillingDetails
import com.sartor.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _isPaymentSuccessful = MutableLiveData<Boolean>()
    val isPaymentSuccessful: LiveData<Boolean> = _isPaymentSuccessful

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError


    fun initiatePayment(checkoutBillingDetails: CheckoutBillingDetails){
       _isViewLoading.value = true

        CoroutineScope(Dispatchers.IO).launch{
            val checkoutRequest = CheckoutRequest(
                checkoutBillingDetails.amount,
                checkoutBillingDetails.billingAddress,
                checkoutBillingDetails.mobileNumber,
                checkoutBillingDetails.country,
                checkoutBillingDetails.state,
                checkoutBillingDetails.zipcode,
                checkoutBillingDetails.cardName,
                checkoutBillingDetails.cardNumber,
                checkoutBillingDetails.cardExpireMonth,
                "20"+checkoutBillingDetails.cardExpireYear,
                checkoutBillingDetails.cvv
            )
            repositoryImpl.makePayment(checkoutRequest, object : OperationCallback<CheckoutResponse>{
                override fun onSuccess(data: CheckoutResponse?) {
                    _isViewLoading.postValue(false)
                    _isPaymentSuccessful.postValue(true)
                }

                override fun onError(error: String?) {
                    _isViewLoading.postValue(false)
                    _onMessageError.postValue(error!!)
                    _isPaymentSuccessful.postValue(false)
                }

            })
        }




    }


}