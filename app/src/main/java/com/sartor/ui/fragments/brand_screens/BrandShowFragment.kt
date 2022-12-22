package com.sartor.ui.fragments.brand_screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Brands
import com.sartor.databinding.FragmentSellerProfileBinding
import com.sartor.ui.adapters.SellerProfileViewPagerAdapter
import com.sartor.ui.adapters.SellersAdapter
import com.sartor.ui.fragments.blog_screens.BlogViewModel
import com.sartor.ui.fragments.home_screens.HomeFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.HashMap

@AndroidEntryPoint
class SellerProfileFragment : Fragment() {


    var binding: FragmentSellerProfileBinding? = null
    private val bViewModel: BlogViewModel by viewModels()

    private val args: SellerProfileFragmentArgs by navArgs()

    private lateinit var sharedPreference: SharedPreference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = SharedPreference(requireContext())

        // Inflate the layout for this fragment
        binding = FragmentSellerProfileBinding.inflate(inflater)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SellerProfileOverviewFragment(),
            SellerProfileShopFragment()
        )

        //61cdaac0b5a0787a63b7f679
        //61b6eeeb3e05a78a00f99e91
        //61b956a6f771aab556d71e4d


        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }
//        binding?.tvSellerUsername.text="farhan"


        setData()
        binding?.vpSellerProfile?.adapter = SellerProfileViewPagerAdapter(fragments, this)

        TabLayoutMediator(
            binding?.tabLayout!!,
            binding?.vpSellerProfile!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.setIcon(R.drawable.ic_overview)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_black_bag)
                    }
                }
            }).attach()

        binding?.chat?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_sellerProfileFragment_to_chatActivity
            )
        }

        binding?.btnWebsite?.setOnClickListener {

            /*
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_sellerProfileFragment_to_brandFragment
            )*/
            var obj = args.brandID

            val directions = SellerProfileFragmentDirections.actionSellerProfileFragmentToBrandFragment(Gson().toJson(obj))
            Navigation.createNavigateOnClickListener(directions)


            /*     val defaultBrowser =
                     Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
                 defaultBrowser.data = Uri.parse(Constant.BASE_URL)
                 startActivity(defaultBrowser)*/
        }

        return binding?.root
    }

    private fun setData() {
        var obj = args.brandID

        val brand = Gson().fromJson(
            obj,
            Brands::class.java
        )
        if (brand.brandID!=null) {

            sharedPreference.save("brandId", brand.brandID)
            binding?.tvSellerUsername?.text = brand.title
            binding?.tvLikesCounter?.text = brand.likes.size.toString()
            binding?.ivSellerImg?.placeImage(Constant.BASE_URL + brand.image)
        } else {

            val response = Gson().fromJson(
                obj,
                ProductResponse::class.java
            )

            sharedPreference.save("brandId", response.brands.id)
            binding?.tvSellerUsername?.text = response.brands.title
            binding?.tvLikesCounter?.text = response.brands.likes.size.toString()
            binding?.ivSellerImg?.placeImage(Constant.BASE_URL + response.brands.img)

        }


    }


}