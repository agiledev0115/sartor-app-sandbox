package com.sartor.ui.fragments.discovery_screens

import android.os.Bundle
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
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Brands
import com.sartor.data.model.myModels.MyBrands
import com.sartor.data.model.myModels.Product
import com.sartor.databinding.FragmentDiscoveryBinding
import com.sartor.ui.adapters.BlogsAdapter
import com.sartor.ui.adapters.BrandsAdapterD
import com.sartor.ui.adapters.SellersAdapter
import com.sartor.util.constants.Constant
import com.sartor.util.constants.brandsStatuses
import com.sartor.util.constants.sellers
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

@AndroidEntryPoint
class DiscoveryFragment : Fragment() {

    var binding: FragmentDiscoveryBinding? = null
    private val viewModel: DiscoveryViewModel by viewModels()
    private lateinit var sharedPreference: SharedPreference

    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null
    private var url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = SharedPreference(requireContext())

        // Inflate the layout for this fragment
        binding = FragmentDiscoveryBinding.inflate(inflater)



        binding?.ivSearch?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_discoveryFragment_to_searchFragment
            )
        }

        binding?.ivChats?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_discoveryFragment_to_chatActivity
            )
        }

        viewModel.getBrandList()
//        setUpViewModel()
        sendAndRequestResponse()
        return binding?.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
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

    private val listOfBrands = Observer<List<Brands>> {
        binding?.rvStatus?.adapter = BrandsAdapterD(it, this@DiscoveryFragment.requireContext())
        binding?.rvStatus?.setHasFixedSize(true)

        binding?.rvSellers?.adapter = SellersAdapter(it, this@DiscoveryFragment.requireContext())
        binding?.rvSellers?.setHasFixedSize(true)


    }

    private fun setUpViewModel() {

        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        viewModel.listOfBrands.observe(viewLifecycleOwner, listOfBrands)
//        Toast.makeText(context, listOfBrands, Toast.LENGTH_SHORT).show()
    }


    private fun sendAndRequestResponse() {
        var token = sharedPreference.getToken()

        url = Constant.BASE_URL + "api/brands"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->


                var array = JSONArray(response)
                val list = ArrayList<Brands>()

                for (n in 0 until array.length()) {
                    try {
                        val obj = array.getJSONObject(n)
                        var myBrand = Gson().fromJson(
                            obj.toString(),
                            MyBrands::class.java
                        )



                        list.add(
                            Brands(
                                myBrand.followers,
                                myBrand.likes,
                                myBrand.is_top,
                                myBrand.img,
                                myBrand.title,
                                myBrand._id
                            )
                        )
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                binding?.progressBar?.visibility = View.GONE
                binding?.rvStatus?.adapter =
                    BrandsAdapterD(list, this@DiscoveryFragment.requireContext())
                binding?.rvStatus?.setHasFixedSize(true)
                binding?.rvSellers?.adapter =
                    SellersAdapter(list, this@DiscoveryFragment.requireContext())
                binding?.rvSellers?.setHasFixedSize(true)


            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Content-Type"] = "application/json"
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }
        }

        mRequestQueue?.add<String>(mStringRequest)
    }


}