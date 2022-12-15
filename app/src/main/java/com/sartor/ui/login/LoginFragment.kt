package com.sartor.ui.login

import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.api.request.LoginRequest
import com.sartor.databinding.FragmentLoginBinding
import com.sartor.util.constants.isEmpty
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)

        navigateHomeScreen()
        navigateForgotPassword()
        navigateSignUp()
        setupViewModel()

        return binding?.root
    }

    private  fun navigateHomeScreen() {
        binding?.btnLogin?.setOnClickListener {
//            binding?.progressBar?.visibility = View.VISIBLE

            if (binding?.etMail!!.isEmpty() == true ||
                binding?.etPassword?.text?.isEmpty() == true ||
                !Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),binding?.etMail!!.text.toString())

            ) {
                Toast.makeText(this.requireContext(), "Please fill in email and password", Toast.LENGTH_SHORT).show()
            } else {

                val email = binding?.etMail!!.text.toString()
                val password = binding?.etPassword!!.text.toString()
                    loginViewModel.loginUser(LoginRequest(userName =email,password = password ))



            }
        }
    }

    private fun navigateForgotPassword() {
        binding?.tvForgotPassword?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    private fun navigateSignUp() {
        binding?.tvSignUp?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setupViewModel() {
        loginViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        loginViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
       loginViewModel.isLoginSuccessful.observe(viewLifecycleOwner,isLoginSuccessful)
    }

    private val isLoginSuccessful = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        if(it){
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_loginFragment_to_homeActivity)
            activity?.finish()
        }else{

            Toast.makeText(context, "Something happened", Toast.LENGTH_SHORT).show()
        }


    }


    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding?.progressBar?.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("LOGIN", "onMessageError $it")
        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }







    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }






}