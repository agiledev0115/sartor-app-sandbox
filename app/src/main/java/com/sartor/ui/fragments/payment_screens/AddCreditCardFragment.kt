package com.sartor.ui.fragments.payment_screens

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.CheckoutBillingDetails
import com.sartor.data.model.Product
import com.sartor.databinding.FragmentAddCreditCardBinding
import com.sartor.ui.adapters.ProductsAdapter2
import com.sartor.util.constants.Constant
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.HashMap

@AndroidEntryPoint
class AddCreditCardFragment : Fragment() {

    var binding: FragmentAddCreditCardBinding? = null
    private lateinit var sharedPreference: SharedPreference
    private val paymentViewModel: PaymentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddCreditCardBinding.inflate(inflater)


        sharedPreference = SharedPreference(requireContext())


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null && p0.toString() != "") {
                    val number = Integer.parseInt(p0.toString())
                    if (number > 12) {
                        Toast.makeText(
                            this@AddCreditCardFragment.requireContext(),
                            "Month cannot be greater than 12",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
//
        binding?.etMonth?.addTextChangedListener(textWatcher)

        binding?.btnSecurePayment?.setOnClickListener {

            if (binding?.etCVV?.text?.isEmpty() == true || binding?.etCardName?.text?.isEmpty() == true
                || binding?.etCardNumber?.text?.isEmpty() == true ||
                binding?.etMonth?.text?.isEmpty() == true || binding?.etYear?.text?.isEmpty() == true
            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val checkoutBillingDetails = CheckoutBillingDetails(
                    sharedPreference.getCheckoutDetail().amount,
                    sharedPreference.getCheckoutDetail().billingAddress,
                    sharedPreference.getCheckoutDetail().mobileNumber,
                    sharedPreference.getCheckoutDetail().country,
                    sharedPreference.getCheckoutDetail().state,
                    sharedPreference.getCheckoutDetail().zipcode,
                    binding?.etCardName?.text?.toString()!!,
                    binding?.etCardNumber?.text?.toString()!!,
                    binding?.etMonth?.text?.toString()!!,
                    binding?.etYear?.text?.toString()!!,
                    binding?.etCVV?.text?.toString()!!
                )

//                paymentViewModel.initiatePayment(checkoutBillingDetails)

//                sendPayment()
            }

        }
        setUpViewModel()
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }




        return binding?.root
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        //binding?.progressBar?.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("LOGIN", "onMessageError $it")
        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }

    private val isPaymentSuccess = Observer<Boolean> {
        if (it) {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_addCreditCardFragment_to_orderSummaryFragment
            )
        }
    }


    private fun setUpViewModel() {
        paymentViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        paymentViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        paymentViewModel.isPaymentSuccessful.observe(viewLifecycleOwner, isPaymentSuccess)
    }


    private fun sendPayment() {


        val progress = ProgressDialog(context) //ProgressDialog

        progress.setTitle("Please wait")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.setCancelable(false)
        progress.show()
        val s = sharedPreference.getCheckoutDetail()


        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/checkout"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                var obj = JSONObject(response)
                var msg = obj["msg"].toString()
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

                progress.dismiss()
            },
            Response.ErrorListener { error ->
                progress.dismiss()
                Toast.makeText(context, "error:" + error.message, Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Content-Type"] = "application/json"
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


            override fun getParams(): MutableMap<String, String> {

                val params: MutableMap<String, String> = HashMap()
                params["amount"] = s.amount.toString()
                params["billing"] = s.billingAddress
                params["mobile"] = s.mobileNumber
                params["country"] = s.country
                params["state"] = s.state
                params["zipcode"] = s.zipcode.toString()
                params["cardname"] = s.cardName
                params["cardnumber"] = s.cardNumber
                params["cardExpirmont"] = s.cardExpireMonth
                params["cardExpirYear"] = s.cardExpireYear
                params["cvv"] = s.cvv

                return params
            }
        }


        mRequestQueue?.add<String>(mStringRequest)


    }


}