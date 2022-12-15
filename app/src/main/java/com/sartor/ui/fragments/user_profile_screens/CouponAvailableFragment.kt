package com.sartor.ui.fragments.user_profile_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.sartor.databinding.FragmentCouponAvailableBinding
import com.sartor.ui.adapters.CouponAdapter
import com.sartor.util.LinePagerIndicatorDecoration
import com.sartor.util.constants.coupons


class CouponAvailableFragment : Fragment() {

    var binding: FragmentCouponAvailableBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponAvailableBinding.inflate(inflater)

        binding?.rvCoupons?.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.rvCoupons?.adapter = CouponAdapter(coupons, this.requireContext())

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding?.rvCoupons)

        binding?.indicator?.attachToRecyclerView(binding?.rvCoupons!!, snapHelper)

        return binding?.root
    }

}