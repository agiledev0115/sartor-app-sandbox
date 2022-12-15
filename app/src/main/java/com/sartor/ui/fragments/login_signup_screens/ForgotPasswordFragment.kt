package com.sartor.ui.fragments.login_signup_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    var binding: FragmentForgotPasswordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater)

        navigateMail()
        navigatePhone()
        navigateBack()
        navigateBack2()

        return binding?.root
    }

    private fun navigateMail() {
        binding?.mail?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigate(R.id.action_forgotPasswordFragment_to_forgotPasswordEmailFragment)
        }
    }

    private fun navigatePhone() {
        binding?.phone?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                R.id.action_forgotPasswordFragment_to_forgotPasswordPhoneFragment
            )
        }
    }

    private fun navigateBack() {
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }
    }

    private fun navigateBack2() {
        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }
    }
    
}