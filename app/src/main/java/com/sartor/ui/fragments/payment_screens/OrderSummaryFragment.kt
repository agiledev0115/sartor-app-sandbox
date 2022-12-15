package com.sartor.ui.fragments.payment_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sartor.R
import com.sartor.databinding.FragmentOrderSummaryBinding
import com.sartor.databinding.FragmentOrdersBinding
import kotlinx.coroutines.*

class OrderSummaryFragment : Fragment() {

    var binding: FragmentOrderSummaryBinding? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderSummaryBinding.inflate(inflater)

        bottomSheetBehavior = BottomSheetBehavior.from(binding?.successfulBS!!)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.btnContinueToShop?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_orderSummaryFragment_to_homeFragment2
            )
        }

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

}