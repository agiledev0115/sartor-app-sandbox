package com.sartor.ui.fragments.login_signup_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentForgotPasswordPhoneBinding

class ForgotPasswordPhoneFragment : Fragment() {

    var binding: FragmentForgotPasswordPhoneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordPhoneBinding.inflate(inflater)

        navigateBack()
        continueNextScreen()

        return binding?.root
    }

    private fun navigateBack() {
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }
    }

    private fun continueNextScreen() {
        binding?.btnContinue?.setOnClickListener {
            if (binding?.etPhone?.text?.isEmpty() == true) {
                Toast.makeText(this.requireContext(), "Please fill in phone number", Toast.LENGTH_SHORT).show()
            } else {
                Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                    R.id.action_forgotPasswordPhoneFragment_to_createNewPasswordFragment
                )
            }
        }
    }

}