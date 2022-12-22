package com.sartor.ui.fragments.home_screens

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
import androidx.navigation.fragment.navArgs
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayoutMediator
import com.sartor.R
import com.sartor.TestActivity
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Product
import com.sartor.data.viewmodels.ProductDescriptionViewModel
import com.sartor.databinding.FragmentProductsDescriptionBinding
import com.sartor.ui.adapters.ProductsAdapter2
import com.sartor.ui.adapters.ProductsDescriptionViewPager
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.HashMap

@AndroidEntryPoint
class ProductsDescriptionFragment : Fragment() {

    var binding: FragmentProductsDescriptionBinding? = null
    var isLiked: Boolean = false
    private val args: ProductsDescriptionFragmentArgs by navArgs()
    private val viewModel: ProductDescriptionViewModel by viewModels()
    lateinit var sp: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sp = SharedPreference(requireContext())
        // Inflate the layout for this fragment
        binding = FragmentProductsDescriptionBinding.inflate(inflater)





        like()
        openBrand()
        buyNow()
        addToBag()
        goBack()
        setDetails()
        setUpViewModel()


        val fragments: ArrayList<Fragment> = arrayListOf(
            TabDescriptionFragment(args.details!!.ProductDetails),
            TabSizeChartFragment(),
            TabReviewFragment()
        )

        binding?.vpProductDescription?.adapter = ProductsDescriptionViewPager(fragments, this)

        TabLayoutMediator(
            binding?.tabLayout!!, binding?.vpProductDescription!!
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Description"
                }
                1 -> {
                    tab.text = "Measure"
                }
                2 -> {
                    tab.text = "Reviews"
                }
            }
        }.attach()

        val slideModel: ArrayList<SlideModel> = arrayListOf()
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[0]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[1]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[2]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[3]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[4]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[5]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[6]))
        slideModel.add(SlideModel(Constant.BASE_URL + args.details!!.productImages[7]))

        binding?.imageSlider?.setImageList(slideModel, scaleType = ScaleTypes.CENTER_CROP)



        binding?.ivSellerImg?.setOnClickListener {


        }

        return binding?.root
    }

    private fun setDetails() {


        binding!!.tvName.text = args.details!!.productName
        binding!!.tvPrice.text = "${Constant.CURRENCY}${args.details!!.productPrice.toString()}"
        binding!!.tvRating.text = args.details!!.productRating.toString()
        binding!!.ivSellerImg.placeImage(Constant.BASE_URL + args.details!!.storeImage)
        binding!!.tvSellerName.text = args.details!!.storeName


        var id = args.details!!.productId

        if (sp.get(id)?.isNotEmpty() == true) {
            isLiked = true
            binding?.ivFavorite?.setImageResource(R.drawable.ic_heart_red)

        } else {
            isLiked = false
            binding?.ivFavorite?.setImageResource(R.drawable.ic_heart)

        }

        sp.save("_pid", id)

    }


    private fun openBrand() {

        binding?.tvSellerName?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_productsDescriptionFragment_to_brandFragment
            )
        }
    }

    private fun like() {
        binding?.ivFavorite?.setOnClickListener {
            if (isLiked) {
                binding?.ivFavorite?.setImageResource(R.drawable.ic_heart)
            } else {
                binding?.ivFavorite?.setImageResource(R.drawable.ic_heart_red)

            }

            updateFavt()
        }
    }

    private fun buyNow() {
        binding?.btnBuyNow?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_productsDescriptionFragment_to_yourShoppingBagFragment
            )
        }
    }

    private fun addToBag() {
        binding?.btnAddtoBag?.setOnClickListener {
            viewModel.addToCart(args.details!!.productId)
        }
    }

    private fun goBack() {
        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

    }

    private val onMessageObserver = Observer<Any> {

        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }

    private fun setUpViewModel() {
        //      viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessage.observe(viewLifecycleOwner, onMessageObserver)
//        viewModel.listOfBlogs.observe(viewLifecycleOwner,listOfBlog)
    }


    private fun updateFavt() {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null

        var id = args.details!!.productId


        var token = sp.getToken()

        var url: String = Constant.BASE_URL + "api/favorite"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->

                if (isLiked) {
                    sp.save(id, "")
                    Toast.makeText(context, "removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "added to favorites", Toast.LENGTH_SHORT).show()
                    sp.save(id, id)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "error:" + error.message, Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


            override fun getParams(): MutableMap<String, String> {

                val params: MutableMap<String, String> = HashMap()
                params["product"] = id
                return params
            }
        }

        mRequestQueue?.add<String>(mStringRequest)
    }

    private fun addToCart() {
        val token = sp.getToken()
        val url = Constant.BASE_URL + "api/carts"
        val mRequestQueue = Volley.newRequestQueue(requireContext())
        val mStringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
//                textView.setText(response)

                Log.d("_fsafdsaf", response.toString())

            },
            Response.ErrorListener { error ->
                Log.i("TestActivity.TAG", "Error :$error")
//                textView.setText(error.toString())
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Content-Type"] = "application/json"
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["product"] = "61b6ef5c3e05a78a00f99e95"
                return params
            }
        }
        mRequestQueue.add<String>(mStringRequest)
    }


}