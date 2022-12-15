package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Trend
import com.sartor.ui.fragments.home_screens.HomeFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage


class TrendingAdapter(private var list: List<ProductResponse>, val context: Context) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    lateinit var sp: SharedPreference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_trending, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list[position].brands.isTop) {

            holder.trend.placeImage(Constant.BASE_URL + list[position].img.img3)
        }


        //list[position].img?.let { holder.trend.setImageResource(it) }


        val directions = HomeFragmentDirections.actionHomeFragment2ToBrandFragment(Gson().toJson(list[position]))
        holder.trend.setOnClickListener(Navigation.createNavigateOnClickListener(directions))

//
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var trend: ImageView = itemView.findViewById(R.id.trendImg)
    }
}