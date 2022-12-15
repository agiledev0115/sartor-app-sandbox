package com.sartor.ui.fragments.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.databinding.FragmentImageViewerBinding
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage

class ImageViewerFragment : Fragment() {

    var binding: FragmentImageViewerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageViewerBinding.inflate(inflater)

        var args = arguments?.let { ImageViewerFragmentArgs.fromBundle(it) }
        val img = args?.image



        if (img != null) {
            binding?.img?.placeImage(Constant.BASE_URL+img)
        }

        binding?.close?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        return binding?.root
    }

}