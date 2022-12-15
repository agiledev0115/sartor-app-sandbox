package com.sartor.ui.fragments.home_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.myModels.MyFavoriteItem
import com.sartor.databinding.FragmentFavoriteBinding
import com.sartor.ui.adapters.FavoritesAdapter
import com.sartor.util.SpacingForRecyclerChild
import com.sartor.util.constants.Constant
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList
import java.util.HashMap


class FavoriteFragment : Fragment() {

    var binding: FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        getFavorites()
        return binding?.root
    }


    private fun getFavorites() {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null
        var sharedPreference = SharedPreference(requireContext())


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/favorite/lists"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                var array = JSONArray(response)


                val list = ArrayList<MyFavoriteItem>()

                for (n in 0 until array.length()) {
                    try {
                        val obj = array.getJSONObject(n)
                        var myBrand = Gson().fromJson(
                            obj.toString(),
                            MyFavoriteItem::class.java
                        )
                        list.add(myBrand)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                binding?.progressBar?.visibility = View.GONE
                setAdapter(list)

            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "error:" + error.message, Toast.LENGTH_SHORT).show()
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


    private fun setAdapter(favorites: ArrayList<MyFavoriteItem>) {

        binding?.rvFavorites?.layoutManager = GridLayoutManager(this.requireContext(), 1)
        binding?.rvFavorites?.adapter = FavoritesAdapter(favorites, this.requireContext())
        binding?.rvFavorites?.addItemDecoration(SpacingForRecyclerChild(16))
        binding?.rvFavorites?.setHasFixedSize(true)


        if (favorites.size == 0) {
            binding?.tvNoFavt?.visibility = View.GONE
        }

    }


}