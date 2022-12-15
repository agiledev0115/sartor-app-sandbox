package com.sartor.ui.fragments.home_screens

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.viewmodels.HomeViewModel
import com.sartor.databinding.FragmentHomeBinding
import com.sartor.ui.adapters.BrandsAdapter
import com.sartor.ui.adapters.ProductsAdapter
import com.sartor.ui.adapters.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.widget.NestedScrollView




@AndroidEntryPoint
class HomeFragment : Fragment() {

    var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)


        openFavourite()
        openFilterDialog()
        openNotifications()
        openShoppingBag()

        homeViewModel.getProductList()
        setUpViewModel()



        binding?.nestedScroll?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (oldScrollY<scrollY){
                binding?.fab?.hide()
            }else{
                binding?.fab?.show()
            }
        })




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

    private val listOfProducts = Observer<List<ProductResponse>> {
        binding?.rvShoppingItems?.setHasFixedSize(true)
        binding?.rvShoppingItems?.adapter = ProductsAdapter(it, this@HomeFragment.requireContext())
        binding?.rvShoppingItems?.layoutManager = StaggeredGridLayoutManager(
            2,
            LinearLayoutManager.VERTICAL
        )

        binding?.rvTrending?.setHasFixedSize(true)
        binding?.rvTrending?.adapter = TrendingAdapter(it, this@HomeFragment.requireContext())


        binding?.rvStatus?.setHasFixedSize(true)
        binding?.rvStatus?.adapter = BrandsAdapter(it, this@HomeFragment.requireContext())


    }

    private fun setUpViewModel() {
        homeViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        homeViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        homeViewModel.listOfProducts.observe(viewLifecycleOwner, listOfProducts)
    }

    private fun openFilterDialog() {

        binding?.ivFilter?.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(this.requireContext())
            dialog.setTitle("Filter")
            val view = layoutInflater.inflate(R.layout.dialog_filter, null)
            dialog.setView(view)

            dialog.setPositiveButton("Apply") { _: DialogInterface, _: Int ->

            }

            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
            dialog.create().show()
        }
    }

    private fun openNotifications() {
        binding?.ivNotification?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_homeFragment2_to_notificationFragment
            )
        }
    }

    private fun openFavourite() {
        binding?.ivFavorites?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost)
                .navigate(
                    R.id.action_homeFragment2_to_favoriteFragment
                )
        }
    }


    private fun openShoppingBag() {
        binding?.fab?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_homeFragment2_to_yourShoppingBagFragment
            )
        }
    }


}