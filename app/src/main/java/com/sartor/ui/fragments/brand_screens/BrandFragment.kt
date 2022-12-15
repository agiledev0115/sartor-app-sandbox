package com.sartor.ui.fragments.brand_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Brands
import com.sartor.databinding.FragmentBrandBinding
import com.sartor.ui.adapters.BrandViewPagerAdapter
import com.sartor.ui.fragments.user_profile_screens.CouponAvailableFragment
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandFragment : Fragment() {

    var binding: FragmentBrandBinding? = null

    private val args: BrandFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBrandBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        val fragments: ArrayList<Fragment> = arrayListOf(
            BrandWebsiteFragment(),
            CouponAvailableFragment(),
        )


        setData()

        binding?.vpBrands?.adapter = BrandViewPagerAdapter(fragments, this)
        binding?.vpBrands?.isUserInputEnabled = false

        TabLayoutMediator(
            binding?.tabLayout!!,
            binding?.vpBrands!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.setIcon(R.drawable.ic_home)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_coupon_icon)
                    }
                }
            }).attach()



        return binding?.root
    }

    private fun setData() {
        var obj = args.data

        val brand = Gson().fromJson(
            obj,
            Brands::class.java
        )
        if (brand.brandID != null) {
            //todo pending until api is updated
        } else {

            var sp = context?.let { SharedPreference(it) }
            sp?.save("intent", obj)
            val data = Gson().fromJson(
                sp?.get("intent"),
                ProductResponse::class.java
            )


            binding?.tvSellerUsername?.text = data.name
            binding?.ivSellerCircle?.placeImage(Constant.BASE_URL + data.brands.img)
            binding?.tvDescription?.text =
                HtmlCompat.fromHtml(data.description ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)

        }


    }

}