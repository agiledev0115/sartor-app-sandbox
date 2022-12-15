package com.sartor.ui.fragments.user_profile_screens

import android.app.Dialog
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.viewmodels.SettingsViewModel
import com.sartor.databinding.FragmentSettingsBinding
import com.sartor.ui.activity.MainActivity
import com.sartor.ui.fragments.home_screens.AddressBookActivity
import com.sartor.util.constants.Constant
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.lang.Exception

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    var binding: FragmentSettingsBinding? = null
    private val settingsViewModel: SettingsViewModel by viewModels()

    lateinit var sp: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sp = SharedPreference(requireContext())


        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.notification?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_settingsFragment_to_notificationFragment
            )
        }

        clickListeners()
        setUpViewModel()


        binding?.tvSignOut?.setOnClickListener {
            settingsViewModel.logoutUser()
        }

        return binding?.root
    }

    private fun clickListeners() {
        binding?.addressBook?.setOnClickListener {
//            showDialog()


            var intent = Intent(getActivity(), AddressBookActivity::class.java)
            startActivity(intent);
            getActivity()?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding?.country?.setOnClickListener {
//            showCountryDialog()

            var intent = Intent(getActivity(), AddressBookActivity::class.java)
            startActivity(intent);
            getActivity()?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding?.clearCache?.setOnClickListener {
            context?.cacheDir?.delete()
            Toast.makeText(context, "cleared", Toast.LENGTH_SHORT).show()
        }

        binding?.rating?.setOnClickListener {
            openRating()
        }


        binding?.connectUs?.setOnClickListener {
            openWeb()
        }


        binding?.about?.setOnClickListener {
            openWeb()
        }

    }

    private fun openRating() {

        val uri: Uri = Uri.parse("market://details?id=com.sartor")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=com.sartor")
                )
            )
        }
    }

    private fun showDialog() {

        /*val customDialog = Dialog(requireActivity())
        customDialog.setContentView(R.layout.dialog_address)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val saveButton = customDialog.findViewById(R.id.btn_save) as TextView
        val etAddress = customDialog.findViewById(R.id.et_address) as EditText

        etAddress.setText(sp.get("address"))

        saveButton.setOnClickListener {
            var txt = etAddress.text.toString().trim()
            sp.save("address", txt)
            Toast.makeText(context, "address saved", Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }
        customDialog.show()*/


        val dialog = MaterialAlertDialogBuilder(this.requireContext())
        dialog.setTitle("Save Address")
        dialog.setCancelable(false)
        val view = layoutInflater.inflate(R.layout.dialog_address, null)
        dialog.setView(view)
        val etAddress = view.findViewById(R.id.et_address) as EditText
        etAddress.setText(sp.get("address"))



        dialog.setPositiveButton("Apply") { _: DialogInterface, _: Int ->
            var txt = etAddress.text.toString().trim()
            sp.save("address", txt)
            Toast.makeText(context, "address saved", Toast.LENGTH_SHORT).show()

        }

        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
        dialog.create().show()
    }

    private fun showCountryDialog() {
/*
        val customDialog = Dialog(requireActivity())
        customDialog.setContentView(R.layout.dialog_country)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val saveButton = customDialog.findViewById(R.id.btn_save) as TextView
        val etCountry = customDialog.findViewById(R.id.et_country) as EditText

        etCountry.setText(sp.get("country"))

        saveButton.setOnClickListener {
            var txt = etCountry.text.toString().trim()
            sp.save("country", txt)
            Toast.makeText(context, "country saved", Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }
        customDialog.show()*/


        val dialog = MaterialAlertDialogBuilder(this.requireContext())
        dialog.setTitle("Save Country")
        dialog.setCancelable(false)
        val view = layoutInflater.inflate(R.layout.dialog_country, null)
        dialog.setView(view)
        val etAddress = view.findViewById(R.id.et_country) as EditText
        etAddress.setText(sp.get("country"))



        dialog.setPositiveButton("Apply") { _: DialogInterface, _: Int ->
            var txt = etAddress.text.toString().trim()
            sp.save("country", txt)
            Toast.makeText(context, "country saved", Toast.LENGTH_SHORT).show()

        }

        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
        dialog.create().show()
    }

    private fun setUpViewModel() {
        //    settingsViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        settingsViewModel.isLoginSuccessful.observe(viewLifecycleOwner, isLoginSuccessful)
    }

    private val isLoginSuccessful = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        if (it) {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        } else {

            Toast.makeText(context, "Something happened", Toast.LENGTH_SHORT).show()
        }


    }

    private fun openWeb() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.BASE_URL))
        browserIntent.setPackage("com.android.chrome") // Whatever browser you are using
        startActivity(browserIntent)
    }
}



