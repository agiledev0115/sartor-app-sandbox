package com.sartor.ui.fragments.login_signup_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentForgotPasswordEmailBinding
import com.sartor.util.constants.isEmail

class ForgotPasswordEmailFragment : Fragment() {


    var binding: FragmentForgotPasswordEmailBinding? = null

    private lateinit var sharedPreference: SharedPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordEmailBinding.inflate(inflater)

        sharedPreference = SharedPreference(requireContext())
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
            if (binding?.etMail?.text?.isEmpty() == true || !binding?.etMail?.text?.toString()!!.isEmail() ) {
                Toast.makeText(this.requireContext(), "Please fill in email", Toast.LENGTH_SHORT).show()
            } else {
                sharedPreference.forgotPasswordEmail(binding?.etMail?.text?.toString()!!)
                Navigation.findNavController(this.requireActivity(), R.id.host).navigate(

                    R.id.action_forgotPasswordEmailFragment_to_createNewPasswordFragment
                )
            }
        }
    }

}