package com.sartor.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentSearchBinding
import com.sartor.ui.adapters.SearchViewPagerAdapter
import java.util.*


class SearchFragment : Fragment() {

    var binding: FragmentSearchBinding? = null

    private lateinit var sharedPreference: SharedPreference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = SharedPreference(requireContext())

        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)

        val fragments: ArrayList<Fragment> = arrayListOf(
            BrandSearchFragment(),
//            QRScanFragment(),
            ProductSearchFragment()
        )

        binding?.vpSearch?.adapter = SearchViewPagerAdapter(fragments, this)

        TabLayoutMediator(
            binding?.tabLayout!!,
            binding?.vpSearch!!
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Brands"
                }
                1 -> {
                    tab.text = "Products"
                }
                //                    2 -> {tab.text = "Products"}
            }
        }.attach()


        return binding?.root
    }


}