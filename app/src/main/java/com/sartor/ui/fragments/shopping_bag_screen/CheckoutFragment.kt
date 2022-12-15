package com.sartor.ui.fragments.shopping_bag_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.Order
import com.sartor.databinding.FragmentCheckoutBinding
import com.sartor.ui.adapters.CheckoutAdapter

val checkoutItems: List<Order> = listOf(
    Order("Nasty gal", 2, 34.0),
    Order("Nasty gal", 3, 51.0),
    Order("Nasty gal", 1, 17.0),
    Order("Nasty gal", 5, 134.0),
    Order("Nasty gal", 3, 51.0),
)

class CheckoutFragment : Fragment() {

    var binding: FragmentCheckoutBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentCheckoutBinding.inflate(inflater)

        binding?.rvCheckout?.layoutManager = LinearLayoutManager(this.requireContext())
        binding?.rvCheckout?.adapter = CheckoutAdapter(checkoutItems, this.requireContext())
        binding?.rvCheckout?.setHasFixedSize(true)

        binding?.ivFavorites?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_checkoutFragment_to_favoriteFragment
            )
        }

        binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        binding?.tvBack?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigateUp()
        }

        return binding?.root
    }

}