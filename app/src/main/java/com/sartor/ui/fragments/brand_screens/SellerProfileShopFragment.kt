package com.sartor.ui.fragments.brand_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Product
import com.sartor.data.model.myModels.MyProduct
import com.sartor.databinding.FragmentSellerProfileShopBinding
import com.sartor.ui.adapters.ProductsAdapter2
import com.sartor.util.SpacingForRecyclerChild
import com.sartor.util.constants.Constant
import org.json.JSONObject
import java.util.*


class SellerProfileShopFragment : Fragment() {

    var binding: FragmentSellerProfileShopBinding? = null
    private lateinit var sharedPreference: SharedPreference

    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPreference= SharedPreference(requireContext())
        // Inflate the layout for this fragment
        binding = FragmentSellerProfileShopBinding.inflate(inflater)


        sendAndRequestResponse()

        return binding?.root
    }


    private fun sendAndRequestResponse() {
        val token = sharedPreference.getToken()
        val brandId = sharedPreference.get("brandId")

        val url = Constant.BASE_URL + "api/brands/products/" + brandId
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->


                var obj = JSONObject(response)

                var product = Gson().fromJson(obj.toString(), MyProduct::class.java)

                if(product!=null&&obj.length()>0){

                    val list = ArrayList<Product>()

                list.add(
                    Product(
                        product.name,
                        product.price.toString(),
                        product.store,
                        product.img.img1,
                        product._id
                    )
                )

                setAdapter(list)
                }
                binding?.progressBar?.visibility=View.GONE

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

    private fun setAdapter(products: ArrayList<Product>) {

        binding?.rvSellerShop?.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding?.rvSellerShop?.adapter = ProductsAdapter2(products, this.requireContext())
        binding?.rvSellerShop?.addItemDecoration(SpacingForRecyclerChild(16))
        binding?.rvSellerShop?.setHasFixedSize(true)

    }
}