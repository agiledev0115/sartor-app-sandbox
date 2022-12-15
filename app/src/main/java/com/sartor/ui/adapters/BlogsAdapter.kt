package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.model.Blog
import com.sartor.ui.fragments.blog_screens.BlogFragmentDirections
import com.sartor.ui.fragments.blog_screens.BlogReadFragmentArgs
import com.sartor.util.constants.Constant.BASE_URL
import com.sartor.util.constants.placeImage

class BlogsAdapter(val list: List<BlogResponse>, val context: Context) :
    RecyclerView.Adapter<BlogsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_blog, parent, false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.body.text = Html.fromHtml(list[position].story, Html.FROM_HTML_MODE_COMPACT)
        holder.img.placeImage(BASE_URL + list[position].image)

        holder.container.setOnClickListener {

            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                BlogFragmentDirections.actionBlogFragmentToBlogReadFragment(Gson().toJson(list[position]))
            )
        /*
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                BlogFragmentDirections.actionBlogFragmentToBlogReadFragment(list[position].id)
            )*/
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.blogTitle)
        var body: TextView = itemView.findViewById(R.id.blogBody)
        var img: ImageView = itemView.findViewById(R.id.blogImage)
        var container: ConstraintLayout = itemView.findViewById(R.id.container)
    }
}