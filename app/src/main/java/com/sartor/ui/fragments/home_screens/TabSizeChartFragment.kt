package com.sartor.ui.fragments.home_screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentTabSizeChartBinding
//import guide.tailor.WebStart

class TabSizeChartFragment : Fragment() {

    var binding: FragmentTabSizeChartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabSizeChartBinding.inflate(inflater)

        binding?.sizeChart?.setOnClickListener{

            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_productsDescriptionFragment_to_measurementFragment
            )
        }

        return binding?.root
    }

}