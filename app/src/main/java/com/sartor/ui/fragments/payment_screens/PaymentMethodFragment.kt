package com.sartor.ui.fragments.payment_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentPaymentMethodBinding

class PaymentMethodFragment : Fragment() {

    var binding: FragmentPaymentMethodBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentMethodBinding.inflate(inflater)

        binding?.lyScan?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_paymentMethodFragment_to_scanCreditCardActivity
            )
        }

        binding?.lyVisa?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_paymentMethodFragment_to_addCreditCardFragment
            )
        }

        binding?.lyMaster?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_paymentMethodFragment_to_addCreditCardFragment
            )
        }

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        return binding?.root
    }

}