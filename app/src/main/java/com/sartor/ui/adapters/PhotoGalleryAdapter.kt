package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.ProductImage
import com.sartor.ui.fragments.brand_screens.SellerProfileFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage

class PhotoGalleryAdapter(val list: List<ProductImage>, val context: Context) : RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_photo_gallery, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //ADD ICON BY TYPE DATA GALLERY
        if(position == 0 || position == 2 || position == 5){
            holder.iconImage.visibility = View.VISIBLE
        }else{
            holder.iconVideo.visibility = View.VISIBLE
        }

        holder.img.placeImage(Constant.BASE_URL+list[position].image)
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
        val iconImage: ImageView = itemView.findViewById(R.id.iconImage)
        val iconVideo: ImageView = itemView.findViewById(R.id.iconVideo)
    }
}