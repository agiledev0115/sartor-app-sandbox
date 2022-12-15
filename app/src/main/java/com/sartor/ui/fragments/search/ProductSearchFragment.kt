package com.sartor.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentProductSearchBinding
import com.sartor.ui.adapters.ProductsAdapter4
import com.sartor.util.SpacingForRecyclerChild
import com.sartor.util.constants.Constant
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set

class ProductSearchFragment : Fragment() {

    var binding: FragmentProductSearchBinding? = null


    private lateinit var sharedPreference: SharedPreference

    private lateinit var list: ArrayList<ProductResponse>
    private lateinit var filteredList: ArrayList<ProductResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPreference = SharedPreference(requireContext())
        binding = FragmentProductSearchBinding.inflate(inflater)

        getProducts()
        search()
//        return inflater.inflate(R.layout.fragment_product_search, container, false)


        return binding?.root
    }

    private fun search() {

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {

                filteredList = ArrayList<ProductResponse>()

                if (query.isNotEmpty()) {
                    for (n in 0 until list.size) {
                        var txt = query.toLowerCase()
                        val product = list[n]
                        val title = product.name.toLowerCase()

                        if (title.contains(txt)) {
                            filteredList.add(product)
                        }
                    }
                }




                setAdapter()

                return false
            }
        })
    }

    private fun setAdapter() {

        binding?.rvProducts?.layoutManager = GridLayoutManager(this.requireContext(), 1)
        binding?.rvProducts?.adapter = ProductsAdapter4(filteredList, this.requireContext())
//        binding?.rvProducts?.addItemDecoration(SpacingForRecyclerChild(56))
        binding?.rvProducts?.setHasFixedSize(true)


        if (filteredList.size == 0) {
            binding?.tvNotFound?.visibility = View.VISIBLE
            binding?.ivSearch?.visibility = View.VISIBLE
        } else {
            binding?.tvNotFound?.visibility = View.GONE
            binding?.ivSearch?.visibility = View.GONE
        }

    }

    private fun getProducts() {

        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null

        var token = sharedPreference.getToken()

        val url = Constant.BASE_URL + "api/product"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                list = ArrayList()

                var array = JSONArray(response)

                for (n in 0 until array.length()) {
                    try {
                        /*val obj = array.getJSONObject(n)
                        var product = Gson().fromJson(
                            obj.toString(),
                            MyProduct::class.java
                        )

                        list.add(
                            Product(
                                product.name,
                                product.price.toString(),
                                product.store,
                                product.img.toString(),
                                product._id
                            )
                        )*/


                        val obj = array.getJSONObject(n)
                        var product = Gson().fromJson(
                            obj.toString(),
                            ProductResponse::class.java
                        )

                        list.add(product)


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                binding?.progressBar?.visibility = View.GONE


//                binding?.rvStatus?.adapter =
//                    BrandsAdapterD(list, this@DiscoveryFragment.requireContext())
//                binding?.rvStatus?.setHasFixedSize(true)
//                binding?.rvSellers?.adapter =
//                    SellersAdapter(list, this@DiscoveryFragment.requireContext())
//                binding?.rvSellers?.setHasFixedSize(true)


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