package com.sartor.ui.fragments.brand_screens

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentBrandWebsiteBinding
import com.sartor.util.constants.Constant


class BrandWebsiteFragment : Fragment() {


    var binding: FragmentBrandWebsiteBinding? = null
    var playing = true

    lateinit var sp: SharedPreference
    lateinit var data: ProductResponse


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sp = context?.let { SharedPreference(it) }!!






        setData();

        binding = FragmentBrandWebsiteBinding.inflate(inflater)


        binding?.playButton?.setOnClickListener {

            if (playing) {
                binding?.video?.pause()
                binding?.playButton?.visibility = View.VISIBLE
                playing = false
            } else {
                binding?.video?.start()
                binding?.playButton?.visibility = View.GONE
                playing = true
            }

        }

        binding?.video?.clipToOutline = true

        return binding?.root
    }

    private fun setData() {

        val data = Gson().fromJson(
            sp.get("intent"),
            ProductResponse::class.java
        )



        val url=Constant.BASE_URL+data.img.img0;

//        val uri = Uri.parse(Constant.BASE_URL+data.img.img0)
//        val mediaController = MediaController(context)
//        mediaController.setAnchorView(binding?.video)
//        binding?.video?.setMediaController(mediaController)
//        binding?.video?.setVideoURI(Uri.parse(url))
//        binding?.video?.start()
        val uri = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.intro)
        binding?.video?.setVideoURI(uri)


        if (playing) {
            binding?.video?.start()
        }

        binding?.video?.setOnClickListener {

            if (playing) {
                binding?.video?.pause()
                binding?.playButton?.visibility = View.VISIBLE
                playing = false
            } else {
                binding?.video?.start()
                binding?.playButton?.visibility = View.GONE
                playing = true
            }

        }



    }

}