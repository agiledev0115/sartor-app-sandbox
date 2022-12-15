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
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Brands
import com.sartor.data.model.myModels.MyBrands
import com.sartor.databinding.FragmentBrandSearchBinding
import com.sartor.ui.adapters.BrandsAdapterDSearch
import com.sartor.util.SpacingForRecyclerChild
import com.sartor.util.constants.Constant
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set


class BrandSearchFragment : Fragment() {
    var binding: FragmentBrandSearchBinding? = null


    private lateinit var sharedPreference: SharedPreference

    private lateinit var list: ArrayList<Brands>
    private lateinit var filteredList: ArrayList<Brands>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = SharedPreference(requireContext())


        binding = FragmentBrandSearchBinding.inflate(inflater)



        getBrands()
        search()
        return binding?.root

    }

    private fun search() {

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {


                return false
            }

            override fun onQueryTextChange(query: String): Boolean {

                filteredList = ArrayList<Brands>()

                if (query.isNotEmpty()){
                    for (n in 0 until list.size) {
                        var txt = query.toLowerCase()
                        val brand = list[n]
                        val title = brand.title.toLowerCase()

                        if (title.contains(txt)) {
                            filteredList.add(brand)
                        }
                    }
                }




                setAdapter()

                return false
            }
        })
    }

    private fun setAdapter() {

        binding?.rvBrands?.layoutManager = GridLayoutManager(this.requireContext(), 1)
        binding?.rvBrands?.adapter = BrandsAdapterDSearch(filteredList, this.requireContext())
        binding?.rvBrands?.setHasFixedSize(true)


        if (filteredList.size == 0) {
            binding?.tvNotFound?.visibility = View.VISIBLE
            binding?.ivSearch?.visibility = View.VISIBLE
        } else {
            binding?.tvNotFound?.visibility = View.GONE
            binding?.ivSearch?.visibility = View.GONE
        }

    }

    private fun getBrands() {

        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null

        var token = sharedPreference.getToken()

        val url = Constant.BASE_URL + "api/brands"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                list = ArrayList<Brands>()

                var array = JSONArray(response)

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
//
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