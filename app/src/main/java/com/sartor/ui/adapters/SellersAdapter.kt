package com.sartor.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.Brands
import com.sartor.ui.fragments.discovery_screens.DiscoveryFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import org.json.JSONObject
import java.util.HashMap

class SellersAdapter(private val list: List<Brands>, val context: Context) :
    RecyclerView.Adapter<SellersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_sellers, parent, false
            )
        )
    }

    lateinit var brand: Brands

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        brand = list[position]
        var count = brand.likes.size
        var countComments = brand.followers.size
        val brandID = brand.brandID

        holder.name.text = brand.title
        holder.imgLogo.placeImage(brand.fullImageUrl())
        holder.imgMain.placeImage(brand.fullImageUrl())
        holder.comment.text = brand.followers.size.toString()
        holder.likes.text = brand.likes.size.toString()

//        holder.send.setOnClickListener {
//            if (countComments == list[position].noComments) {
//                countComments = list[position].noComments + 1
//                holder.comment.text = (countComments).toString()
//            } else {
//                countComments += 1
//                holder.comment.text = (countComments).toString()
//            }
//            holder.commentSection.setText("")
//        }


        val direction = DiscoveryFragmentDirections.actionDiscoveryFragmentToSellerProfileFragment(
            Gson().toJson(brand)
        )


        holder.imgLogo.setOnClickListener(Navigation.createNavigateOnClickListener(direction))

        holder.imgMain.setOnClickListener(Navigation.createNavigateOnClickListener(direction))

//        holder.likeBtn.setOnClickListener {
//            if (count == list[position].noLikes) {
//                count = list[position].noLikes + 1
//                holder.likes.text = (count).toString()
//            } else {
//                count = list[position].noLikes
//                holder.likes.text = (count).toString()
//            }
//
//        }

        holder.likeBtn.setOnClickListener(View.OnClickListener {
            updateLike(holder)
        })

    }

    private fun updateLike(holder: ViewHolder) {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null
        var sharedPreference = SharedPreference(context)


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/brands/likes/" + brand.brandID
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                var obj = JSONObject(response)
                var msg = obj["msg"].toString()
                var size = brand.likes.size;

                if (msg == "Like Brand") {
                    size++
                    Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show()
                } else {
                    size--
                    Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT).show()
                }
                holder.likes.text = size.toString()

                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Content-Type"] = "application/json"
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


        }

        mRequestQueue?.add<String>(mStringRequest)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.brandName)
        var imgLogo: ImageView = itemView.findViewById(R.id.img)
        var imgMain: ImageView = itemView.findViewById(R.id.mainImage)
        var comment: TextView = itemView.findViewById(R.id.noComments)
        var likes: TextView = itemView.findViewById(R.id.noLikes)
        var likeBtn: ImageView = itemView.findViewById(R.id.like)
        var send: ImageView = itemView.findViewById(R.id.ivSendComments)
        var commentSection: EditText = itemView.findViewById(R.id.commentSection)
        var container: ConstraintLayout = itemView.findViewById(R.id.container)
    }
}