package com.sartor.ui.fragments.user_profile_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sartor.R
import com.sartor.databinding.FragmentCouponsBinding
import com.sartor.ui.adapters.CouponsViewPagerAdapter


class CouponsFragment : Fragment() {

    var binding: FragmentCouponsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponsBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        val fragments: ArrayList<Fragment> = arrayListOf(
            CouponAvailableFragment(),
            CouponExpiredFragment()
        )

        binding?.vpCoupons?.adapter = CouponsViewPagerAdapter(fragments, this)
        binding?.vpCoupons?.isUserInputEnabled = false

        TabLayoutMediator(binding?.tabLayout!!, binding?.vpCoupons!!, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when (position) {
                    0 -> {tab.text = "Available"}
                    1 -> {tab.text = "Expired"}
                }
            }
        }).attach()

        return binding?.root
    }


}