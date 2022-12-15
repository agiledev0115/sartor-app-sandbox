package com.sartor.ui.fragments.home_screens

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Review
import com.sartor.data.model.myModels.MyReviewItem
import com.sartor.databinding.FragmentTabReviewBinding
import com.sartor.ui.adapters.ReviewAdapter
import com.sartor.util.constants.Constant
import org.json.JSONArray
import org.json.JSONException
import java.util.*


class TabReviewFragment : Fragment() {

    lateinit var sp: SharedPreference
    var binding: FragmentTabReviewBinding? = null
    lateinit var pId: String

    var r1 = 0
    var r2 = 0
    var r3 = 0
    var r4 = 0
    var r5 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sp = context?.let { SharedPreference(it) }!!

        pId = sp.get("_pid")!!
        sendAndRequestResponse()
        binding = FragmentTabReviewBinding.inflate(inflater)!!


        return binding?.root
    }


    private fun sendAndRequestResponse() {
        val token = sp.getToken()

        val url = Constant.BASE_URL + "api/reviews"
        var mRequestQueue = Volley.newRequestQueue(context)
        var mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->

                val list = ArrayList<Review>()
                var array = JSONArray(response)
                binding?.progressBar?.visibility = View.GONE


                for (n in 0 until array.length()) {
                    try {
                        val obj = array.getJSONObject(n)
                        var myReview = Gson().fromJson(
                            obj.toString(),
                            MyReviewItem::class.java
                        )



                        if (myReview.product._id == pId) {
                            list.add(
                                Review(
                                    myReview.customer.full_name,
                                    myReview.rate,
                                    myReview.created_at,
                                    myReview.review,
                                    myReview.customer.picture
                                )
                            )
                            when (myReview.rate) {
                                1 -> r1++
                                2 -> r2++
                                3 -> r3++
                                4 -> r4++
                                5 -> r5++
                            }
                        }





                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }

                setAdapter(list)
                setProgressbars()
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


    private fun setAdapter(list: ArrayList<Review>) {

        binding?.rvReview?.layoutManager = LinearLayoutManager(this.requireContext())!!
        binding?.rvReview?.adapter = ReviewAdapter(list, this.requireContext())
        binding?.rvReview?.setHasFixedSize(true)

        if (list.size == 0) {
            binding?.tvNoReviews?.visibility = View.VISIBLE
        }

    }


    private fun setProgressbars() {


        binding?.tv1?.text = r1.toString()
        binding?.tv2?.text = r2.toString()
        binding?.tv3?.text = r3.toString()
        binding?.tv4?.text = r4.toString()
        binding?.tv5?.text = r5.toString()


        var total = (r1 + r2 + r3 + r4 + r5).toDouble()



        var a=1.0
        var b=2.0

        var p = (r3.toDouble() / total.toDouble()) * 100



        Log.d("sdjlask", "$r3--$total---$p")

        binding?.progressBar1?.progress = ((r1 / total) * 100).toInt()!!
        binding?.progressBar2?.progress = ((r2 / total) * 100).toInt()
        binding?.progressBar3?.progress = ((r3 / total) * 100).toInt()
        binding?.progressBar4?.progress = ((r4 / total) * 100).toInt()
        binding?.progressBar5?.progress = ((r5 / total) * 100).toInt()

    }

}