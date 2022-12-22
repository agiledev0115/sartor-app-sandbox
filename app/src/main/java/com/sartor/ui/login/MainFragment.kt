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
import com.sartor.databinding.FragmentMainBinding
import com.sartor.util.constants.isEmpty
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)

        navigateLogin()
        navigateDiscovey()
        navigateRegistery()


        return binding?.root
    }



    private fun navigateLogin() {
        binding?.btnToLogin?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    private fun navigateDiscovey() {
        binding?.btnToExploer?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_mainFragment_to_homeActivity)
        }
    }
    private fun navigateRegistery() {
        binding?.btnToRegister?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host)
                .navigate(R.id.action_mainFragment_to_registerFragment)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}