package com.sartor.ui.fragments.user_profile_screens

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.myModels.Product
import com.sartor.databinding.FragmentWishlistBinding
import com.sartor.util.constants.Constant
import org.json.JSONObject
import java.util.HashMap


class WishlistFragment : Fragment() {

    var binding: FragmentWishlistBinding? = null

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
        binding = FragmentWishlistBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.img?.setOnClickListener {
            binding?.img?.setImageResource(R.drawable.avd_anim_wishlist)
            val anim = binding?.img?.drawable as AnimatedVectorDrawable
            anim.start()
        }

        binding?.btnTryAgain?.setOnClickListener {
            binding?.img?.setImageResource(R.drawable.avd_anim_wishlist)
            val anim = binding?.img?.drawable as AnimatedVectorDrawable
            anim.start()
        }

        sendAndRequestResponse()
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        binding?.img?.setImageResource(R.drawable.avd_anim_wishlist)
        val anim = binding?.img?.drawable as AnimatedVectorDrawable
        anim.start()
    }

    override fun onResume() {
        binding?.img?.setImageResource(R.drawable.avd_anim_wishlist)
        val anim = binding?.img?.drawable as AnimatedVectorDrawable
        anim.start()
        super.onResume()
    }


    private fun sendAndRequestResponse() {

        var token = sharedPreference.getToken()

        url = Constant.BASE_URL + "api/brands/wishlists"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
//                textView.setText(response)
                //set Adapter
                Log.d("_wishList", response)


                var obj = JSONObject(response)

                var product = Gson().fromJson(obj.toString(), Product::class.java)


            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
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