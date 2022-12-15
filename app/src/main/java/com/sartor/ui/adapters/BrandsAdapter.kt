package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.model.BrandStatus
import com.sartor.ui.fragments.home_screens.HomeFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage

class BrandsAdapter(private var list: List<ProductResponse>, val context: Context) : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_status, parent,false
            )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.placeImage(Constant.BASE_URL + list[position].brands.img)
        holder.title.text = list[position].brands.title

         val direction = HomeFragmentDirections.actionHomeFragment2ToSellerProfileFragment(Gson().toJson(list[position]))
        holder.img.setOnClickListener (
            Navigation.createNavigateOnClickListener(direction)
                )


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val title : TextView = itemView.findViewById(R.id.brandName)
    }
}