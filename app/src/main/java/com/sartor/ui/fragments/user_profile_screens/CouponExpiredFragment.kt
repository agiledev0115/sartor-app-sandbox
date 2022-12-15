package com.sartor.ui.fragments.user_profile_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.sartor.databinding.FragmentCouponExpiredBinding
import com.sartor.ui.adapters.CouponGreyAdapter
import com.sartor.util.LinePagerIndicatorDecoration
import com.sartor.util.constants.coupons

class CouponExpiredFragment : Fragment() {

    var binding: FragmentCouponExpiredBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponExpiredBinding.inflate(inflater)

        binding?.rvCoupons?.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.rvCoupons?.adapter = CouponGreyAdapter(coupons, this.requireContext())


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding?.rvCoupons)

        binding?.indicator?.attachToRecyclerView(binding?.rvCoupons!!, snapHelper)



//        binding?.indicator?.attachTo(binding?.rvCoupons!!)


        return binding?.root
    }
}