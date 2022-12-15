package com.sartor.ui.fragments.brand_screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.ProductImage
import com.sartor.data.model.myModels.MyProduct
import com.sartor.data.model.myModels.Product
import com.sartor.data.viewmodels.HomeViewModel
import com.sartor.databinding.FragmentSellerProfileOverviewBinding
import com.sartor.ui.adapters.*
import com.sartor.util.SpacingForRecyclerChild
import com.sartor.util.constants.Constant
import com.sartor.util.constants.photoGallery
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class SellerProfileOverviewFragment : Fragment() {

    var binding: FragmentSellerProfileOverviewBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var sharedPreference: SharedPreference

    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = SharedPreference(requireContext())

        // Inflate the layout for this fragment
        binding = FragmentSellerProfileOverviewBinding.inflate(inflater)


/*

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }
*/


        getData()
        return binding?.root
    }

    private fun getData() {

        sendAndRequestResponse()

    }


    private fun sendAndRequestResponse() {
        val token = sharedPreference.getToken()
        val brandId = sharedPreference.get("brandId")

        val url = Constant.BASE_URL + "api/brands/products/" + brandId
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
//                textView.setText(response)
                //set Adapter


                var obj = JSONObject(response)

                if(obj!=null&&obj.length()>0){
                    var product = Gson().fromJson(obj.toString(), MyProduct::class.java)
                    var images = product.img

                    val productImages = ArrayList<ProductImage>()
                    productImages.add(ProductImage(images.img0))
                    productImages.add(ProductImage(images.img1))
                    productImages.add(ProductImage(images.img2))
                    productImages.add(ProductImage(images.img3))
                    productImages.add(ProductImage(images.img4))
                    productImages.add(ProductImage(images.img5))
                    productImages.add(ProductImage(images.img6))
                    productImages.add(ProductImage(images.img7))
                    setAdapter(productImages)
                }else{
                    Toast.makeText(context, "no products found", Toast.LENGTH_SHORT).show()
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

    private fun setAdapter(productImages: ArrayList<ProductImage>) {

        binding?.rvPhotoGallery?.layoutManager = GridLayoutManager(this.requireContext(), 3)
        binding?.rvPhotoGallery?.adapter = PhotoGalleryAdapter(productImages, this.requireContext())

        binding?.rvPhotoGallery?.setHasFixedSize(true)
        binding?.rvPhotoGallery?.addItemDecoration(SpacingForRecyclerChild(4))

    }

}