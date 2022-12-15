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
import com.sartor.databinding.FragmentCreateNewPasswordBinding

class CreateNewPasswordFragment : Fragment() {

    var binding: FragmentCreateNewPasswordBinding? = null
    private lateinit var sharedPreference: SharedPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNewPasswordBinding.inflate(inflater)

        sharedPreference = SharedPreference(requireContext())
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.host).navigateUp()
        }

        binding?.btnContinue?.setOnClickListener {
            if (binding?.etPassword?.text?.isEmpty() == true) {
                Toast.makeText(this.requireContext(), "Create new password cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                sharedPreference.forgotPasswordNew(binding?.etPassword?.text?.toString()!!)
                Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                    R.id.action_createNewPasswordFragment_to_confirmPasswordFragment
                )
            }
        }

        return binding?.root
    }

}