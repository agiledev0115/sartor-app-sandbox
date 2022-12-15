package com.sartor.ui.register

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.api.request.SignUpRequest
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentRegisterBinding
import com.sartor.ui.register.RegisterViewModel
import com.sartor.util.constants.isEmail
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    var binding: FragmentRegisterBinding? = null

    private lateinit var sharedPreference : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        sharedPreference = SharedPreference(requireContext())
        navigateBack()
        navigateLogin()
        registerUser()


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

    private fun navigateLogin() {
        binding?.tvLogin?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }
    }

    private fun registerUser() {
        navigateHomeScreen()
    }

    private fun navigateHomeScreen() {
        binding?.btnRegister?.setOnClickListener {
            if (binding?.etMail?.text?.isEmpty() == true || binding?.etPassword?.text?.isEmpty() == true
                || binding?.etPassword2?.text?.isEmpty() == true ||  !binding?.etMail!!.text.toString().isEmail() ||
                binding?.etPassword2?.text?.toString() != binding?.etPassword?.text?.toString()

            ) {
                Toast.makeText(this.requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {

                sharedPreference.saveEmail(binding?.etMail?.text?.toString()!!)
                sharedPreference.savePassword(binding?.etPassword?.text?.toString()!!)
                Navigation.findNavController(this.requireActivity(), R.id.host).navigate(R.id.action_registerFragment_to_setupFragment)
            }
        }
    }











    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
    
}