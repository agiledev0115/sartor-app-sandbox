package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.Review
import com.sartor.ui.fragments.brand_screens.SellerProfileFragmentDirections
import com.sartor.ui.fragments.brand_screens.SellerProfileFragmentDirections.actionSellerProfileFragmentToImageViewerFragment
import com.sartor.ui.fragments.home_screens.ProductsDescriptionFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage

class ReviewAdapter(val list: List<Review>, val context: Context) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.child_product_reviews, parent, false
          )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.review.text = list[position].review
//        holder.img.setImageResource(list[position].img)

        holder.img.placeImage(Constant.BASE_URL+list[position].img)
      /*  holder.img.setOnClickListener {
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                ProductsDescriptionFragmentDirections.actionProductsDescriptionFragmentToImageViewerFragment(list[position].img)
            )
        }*/


        var date=list[position].date
        holder.date.text = date.substring(0,10)

        var rating=list[position].rating

        if (rating>0){
            holder.star1.visibility=View.VISIBLE
        }else{
            holder.star1.visibility=View.GONE
        }


        if (rating>1){
            holder.star2.visibility=View.VISIBLE
        }else{
            holder.star2.visibility=View.GONE
        }


        if (rating>2){
            holder.star3.visibility=View.VISIBLE
        }else{
            holder.star3.visibility=View.GONE
        }


        if (rating>3){
            holder.star4.visibility=View.VISIBLE
        }else{
            holder.star4.visibility=View.GONE
        }

        if (rating>4){
            holder.star5.visibility=View.VISIBLE
        }else{
            holder.star5.visibility=View.GONE
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvReviewerName)
        var star1: ImageView = itemView.findViewById(R.id.star1)
        var star2: ImageView = itemView.findViewById(R.id.star2)
        var star3: ImageView = itemView.findViewById(R.id.star3)
        var star4: ImageView = itemView.findViewById(R.id.star4)
        var star5: ImageView = itemView.findViewById(R.id.star5)
        var date: TextView = itemView.findViewById(R.id.tvDate)
        var review: TextView = itemView.findViewById(R.id.tvReview)
        var img: ImageView = itemView.findViewById(R.id.img)
    }
}