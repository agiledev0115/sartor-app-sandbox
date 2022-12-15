package com.sartor.ui.fragments.shopping_bag_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Cart
import com.sartor.data.viewmodels.ShoppingBagViewModel
import com.sartor.databinding.FragmentYourShoppingBagBinding
import com.sartor.ui.adapters.ShoppingBagItemAdapter
import com.sartor.util.constants.Constant.CURRENCY
import com.sartor.util.constants.bagItems
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YourShoppingBagFragment : Fragment() {

    var binding: FragmentYourShoppingBagBinding? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel : ShoppingBagViewModel by viewModels()
    private lateinit var sharedPreference : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentYourShoppingBagBinding.inflate(inflater)

        bottomSheetBehavior = BottomSheetBehavior.from(binding?.bottomSheet!!)

        sharedPreference = SharedPreference(requireContext())
          back()


        try{
//            viewModel.getCart()

        }catch (e:Exception){
            Toast.makeText(context, "error: "+e.message, Toast.LENGTH_SHORT).show()
        }



        try{
            setUpViewModel()

        }catch (e:Exception){
            Toast.makeText(context, "error: "+e.message, Toast.LENGTH_SHORT).show()
        }
        //binding?.rvShoppingBag?.adapter = ShoppingBagItemAdapter(it.cartItems, this.requireContext())
        val lManager = LinearLayoutManager(this.requireContext())
        binding?.rvShoppingBag?.layoutManager = lManager
        binding?.rvShoppingBag?.setHasFixedSize(true)

        binding?.ivFavorites?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_yourShoppingBagFragment_to_favoriteFragment
            )
        }

//        binding?.btnScrollToBottom?.setOnClickListener{
//            lManager.scrollToPosition(bagItems.size - 1)
//        }

        binding?.btnCheckout?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_yourShoppingBagFragment_to_shippingInformationFragment
            )
        }

//        setUpViewModel()
        return binding?.root
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding?.progressBar?.visibility = visibility
    }

    private val cartList = Observer<Cart>{

        binding?.rvShoppingBag?.adapter = ShoppingBagItemAdapter(it.cartItems, this.requireContext())
        binding?.tvPrice?.text = "$CURRENCY${it.total}"
        binding?.tvNoOfItems?.text = "${it.cartItems.size}Items"
        val amountRaw = it.total.replace(",","")
        sharedPreference.saveCheckoutAmount(Integer.parseInt(amountRaw))

    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("LOGIN", "onMessageError $it")
        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }

    private fun setUpViewModel() {
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessage.observe(viewLifecycleOwner, onMessageErrorObserver)
        viewModel.cart.observe(viewLifecycleOwner,cartList)
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun back(){
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

    }

    
}