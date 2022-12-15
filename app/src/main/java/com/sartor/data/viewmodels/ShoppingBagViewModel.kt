package com.sartor.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartor.data.api.OperationCallback
import com.sartor.data.api.request.AddToCartRequest
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.CartResponse
import com.sartor.data.model.Cart
import com.sartor.data.model.CartItem
import com.sartor.data.repositories.RepositoryImpl
import com.sartor.util.constants.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject

@HiltViewModel
class ShoppingBagViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) :ViewModel() {


    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _cart = MutableLiveData<Cart>()
    val cart : LiveData<Cart> = _cart
    private val _onMessage = MutableLiveData<Any>()
    val onMessage: LiveData<Any> = _onMessage


    fun getCart(){

        _isViewLoading.value = true

        val cartItemList  = mutableListOf<CartItem>()
        CoroutineScope(Dispatchers.IO).launch {

            repositoryImpl.getCart( object: OperationCallback<CartResponse> {
                override fun onSuccess(data: CartResponse?) {
                    _isViewLoading.postValue(false)
                      data!!.data.forEach {
                          Log.i("CART",it.product.name)
                          Log.i("CART",it.product.brands.img)
                          Log.i("CART",it.product.price.toString())
                          Log.i("CART",it.product.category.name)
                          Log.i("CART",it.product.qty.toString())





                          cartItemList.add(
                              CartItem(
                                  it.product.name,
                                  it.product.img,
                                  it.product.price.toString(),
                                  it.product.category.name,
                                  it.product.qty

                              )
                          )
                      }



                    _cart.postValue(
                        Cart(cartItemList,data.total))
                    //_onMessage.postValue("Added to cart " )

                }

                override fun onError(error: String?) {
                    _onMessage.postValue(error!!)
                    _isViewLoading.postValue(false)

                }

            })
        }
    }




}