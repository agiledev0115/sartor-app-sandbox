package com.sartor.ui.register

import android.os.Bundle
import android.util.Log
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
import com.sartor.databinding.FragmentSetupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupFragment : Fragment() {

    var binding: FragmentSetupBinding? = null
    private val registerViewModel : RegisterViewModel by viewModels()
    private lateinit var sharedPreference: SharedPreference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetupBinding.inflate(inflater)

        sharedPreference = SharedPreference(requireContext())


        binding?.btnDone?.setOnClickListener {

            if (binding?.etAddress?.text?.isEmpty() == true || binding?.etFullName?.text?.isEmpty() == true
                || binding?.etMobile?.text?.isEmpty() == true) {
                Toast.makeText(this.requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {

                registerViewModel.registerUser(
                    SignUpRequest(
                        sharedPreference.getRegisteredUserData().userName,
                        sharedPreference.getRegisteredUserData().password,
                        binding?.etFullName?.text?.toString()!!,
                        sharedPreference.getRegisteredUserData().userType,
                    )

                )

            }

        }

        binding?.male?.setOnClickListener {
            binding?.male?.setTextColor(resources.getColor(R.color.orange))
            binding?.female?.setTextColor(resources.getColor(R.color.black))
        }

        binding?.female?.setOnClickListener {
            binding?.female?.setTextColor(resources.getColor(R.color.orange))
            binding?.male?.setTextColor(resources.getColor(R.color.black))
        }


      setUpViewModel()

        return binding?.root
    }




    private fun setUpViewModel() {
      registerViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
      registerViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
      registerViewModel.isRegisterSuccessful.observe(viewLifecycleOwner,isRegistrationSuccessful)
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

    private val isRegistrationSuccessful = Observer<Boolean> {
        Log.v("Register", "isViewLoading $it")
        if(it){
            Navigation.findNavController(this.requireActivity(), R.id.host).navigate(
                R.id.action_setupFragment_to_loginFragment
            )

        }else{

            Toast.makeText(context, "Something happened ", Toast.LENGTH_SHORT).show()
        }


    }
    
}