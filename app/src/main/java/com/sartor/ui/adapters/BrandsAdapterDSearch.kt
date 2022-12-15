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

class BrandsAdapterDSearch(private var list: List<Brands>, val context: Context) :
    RecyclerView.Adapter<BrandsAdapterDSearch.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_brand_search, parent, false
            )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.placeImage(list[position].fullImageUrl())
        holder.brandName.text = list[position].title
        val direction =
            SearchFragmentDirections.actionSearchFragmentToSellerProfileFragment(Gson().toJson(list[position]))

        holder.img.setOnClickListener(Navigation.createNavigateOnClickListener(direction))

        var followers = list[position].followers.size

        if (followers ==1) {
            holder.tvFollower.text = "$followers follower"
        }else{
            holder.tvFollower.text = "$followers followers"
        }

    }


    override fun getItemCount(): Int {
        Log.i("Discovery_", list.size.toString())
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val brandName: TextView = itemView.findViewById(R.id.brandName)
        val tvFollower: TextView = itemView.findViewById(R.id.tv_likes)
    }
}