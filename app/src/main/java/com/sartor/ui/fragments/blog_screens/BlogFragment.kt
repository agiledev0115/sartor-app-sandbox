package com.sartor.ui.fragments.blog_screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.BrandResponse
import com.sartor.databinding.FragmentBlogBinding
import com.sartor.ui.adapters.BlogsAdapter
import com.sartor.ui.fragments.brand_screens.BrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogFragment : Fragment() {

    var binding: FragmentBlogBinding? = null
    private val blogViewModel: BlogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlogBinding.inflate(inflater)


        blogViewModel.getBlogList()

        setUpViewModel()
        return binding?.root
    }


    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("LOGIN", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding?.progressBar?.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("LOGIN", "onMessageError $it")
        Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
    }

    private val listOfBlog = Observer<List<BlogResponse>> {
        binding?.rvBlog?.adapter = BlogsAdapter(it, this.requireActivity())
        binding?.rvBlog?.setHasFixedSize(true)
        Log.d("_farhan", it.toString())
    }


    private fun setUpViewModel() {
        blogViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        blogViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        blogViewModel.listOfBlogs.observe(viewLifecycleOwner, listOfBlog)
    }


}