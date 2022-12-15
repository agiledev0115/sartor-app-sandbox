package com.sartor.ui.fragments.login_signup_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentConfirmPasswordBinding

class ConfirmPasswordFragment : Fragment() {

    var binding: FragmentConfirmPasswordBinding? = null
    private lateinit var sharedPreference: SharedPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfirmPasswordBinding.inflate(inflater)


        sharedPreference = SharedPreference(requireContext())
        binding?.btnConfirmPassword?.setOnClickListener {
            if (binding?.etConfirmPassword?.text?.isEmpty() == true ||
                binding?.etConfirmPassword?.text?.toString() != sharedPreference.getForgotPassword().newPassword

            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Confirm password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                    R.id.action_confirmPasswordFragment_to_allSetFragment
                )
            }
        }

        return binding?.root
    }

}