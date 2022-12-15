package com.sartor.ui.fragments.home_screens

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.sartor.R
import com.sartor.databinding.FragmentTabDescriptionBinding

class TabDescriptionFragment(private val description : String ) : Fragment() {

    var binding: FragmentTabDescriptionBinding? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabDescriptionBinding.inflate(inflater)

        binding!!.productsDescription.text = Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT)

        return binding?.root
    }
}