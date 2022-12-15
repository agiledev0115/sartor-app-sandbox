package com.sartor.ui.fragments.payment_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentShippingInformationBinding

class ShippingInformationFragment : Fragment() {

    var binding: FragmentShippingInformationBinding? = null
    private lateinit var sp : SharedPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShippingInformationBinding.inflate(inflater)
        sp = SharedPreference(requireContext())
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.etDeliveryAddress?.setText(sp.get("address"))
        binding?.etCountry?.setText(sp.get("country"))

        binding?.btnChoosePayment?.setOnClickListener {

            if (binding?.etAddPhone?.text?.isEmpty() == true || binding?.etAddPhone2?.text?.isEmpty() == true
                || binding?.etCountry?.text?.isEmpty() == true || binding?.etDeliveryAddress?.text?.isEmpty() == true
                || binding?.etState?.text?.isEmpty() == true || binding?.etZipCode?.text?.isEmpty() == true
            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                sp.saveShippingDetails(
                    binding?.etDeliveryAddress?.text?.toString()!!,
                    binding?.etAddPhone?.text?.toString()!!,
                    binding?.etCountry?.text?.toString()!!,
                    binding?.etState?.text?.toString()!!,
                    Integer.parseInt(binding?.etZipCode?.text?.toString()!!),
                )

                Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                    R.id.action_shippingInformationFragment_to_paymentMethodFragment
                )
            }
        }

        return binding?.root
    }

}