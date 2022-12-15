package com.sartor.ui.fragments.blog_screens

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.BlogResponse
import com.sartor.databinding.FragmentBlogReadBinding
import com.sartor.util.constants.Constant.BASE_URL
import com.sartor.util.constants.placeImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogReadFragment : Fragment() {

    var binding: FragmentBlogReadBinding? = null
    private val blogViewModel: BlogViewModel by viewModels()
    private val args: BlogReadFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentBlogReadBinding.inflate(inflater)

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

//        blogViewModel.getBlog(args.blogID)
//        setUpViewModel()

        setData()

        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setData() {


        var data=args.blogID
        var it=Gson().fromJson(data,BlogResponse::class.java)
        binding?.title?.text = it.title
        binding?.body?.text = Html.fromHtml(it.story, Html.FROM_HTML_MODE_COMPACT)
        binding?.images?.placeImage(BASE_URL + it.image)
        binding?.progressBar?.visibility=View.GONE

    }


//    private val isViewLoadingObserver = Observer<Boolean> {
//        Log.v("LOGIN", "isViewLoading $it")
//        val visibility = if (it) View.VISIBLE else View.GONE
//        binding?.progressBar?.visibility = visibility
//    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("LOGIN", "onMessageError $it")
        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val blog = Observer<BlogResponse> {
        binding?.title?.text = it.title
        binding?.body?.text = Html.fromHtml(it.story, Html.FROM_HTML_MODE_COMPACT)
        binding?.images?.placeImage(BASE_URL + it.image)

        binding?.progressBar?.visibility=View.GONE

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpViewModel() {
        //blogViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        blogViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        blogViewModel.blog.observe(viewLifecycleOwner, blog)
    }


}