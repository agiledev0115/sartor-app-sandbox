package com.sartor.ui.fragments.login_signup_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentAllSetBinding

class AllSetFragment : Fragment() {

    var binding: FragmentAllSetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllSetBinding.inflate(inflater)

        binding?.btnContinueToShop?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                R.id.action_allSetFragment_to_homeActivity
            )
            activity?.finish()
        }

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        binding?.ivAllSet?.animate()?.rotationBy(360F)?.setDuration(600)?.start()
    }
    
}