package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.model.BrandStatus
import com.sartor.data.model.Brands
import com.sartor.ui.fragments.discovery_screens.DiscoveryFragmentDirections
import com.sartor.ui.fragments.search.SearchFragmentDirections
import com.sartor.util.constants.placeImage
import com.sartor.util.constants.showToast

class BrandsAdapterD(private var list: List<Brands>, val context: Context) :
    RecyclerView.Adapter<BrandsAdapterD.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_status, parent, false
            )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.placeImage(list[position].fullImageUrl())
        holder.brandName.text = list[position].title
        val direction = DiscoveryFragmentDirections.actionDiscoveryFragmentToSellerProfileFragment(Gson().toJson(list[position]))

        holder.img.setOnClickListener(Navigation.createNavigateOnClickListener(direction))



    }


    override fun getItemCount(): Int {
        Log.i("Discovery_", list.size.toString())
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val brandName: TextView = itemView.findViewById(R.id.brandName)
    }
}