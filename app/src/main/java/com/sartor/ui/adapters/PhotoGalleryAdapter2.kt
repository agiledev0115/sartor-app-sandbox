package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.ProductImage
import com.sartor.ui.fragments.brand_screens.SellerProfileFragmentDirections
import com.sartor.util.constants.placeImage

class PhotoGalleryAdapter2(val list: List<ProductImage>, val context: Context) : RecyclerView.Adapter<PhotoGalleryAdapter2.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_photo_gallery, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.placeImage(list[position].image)

        holder.img.setOnClickListener {
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                SellerProfileFragmentDirections.actionSellerProfileFragmentToImageViewerFragment(list[position].image)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.photo)
    }
}