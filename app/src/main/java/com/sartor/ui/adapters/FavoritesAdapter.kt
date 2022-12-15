package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.ProductDetails
import com.sartor.data.model.myModels.MyFavoriteItem
import com.sartor.data.model.myModels.ProductX
import com.sartor.ui.fragments.home_screens.FavoriteFragmentDirections
import com.sartor.ui.fragments.search.SearchFragmentDirections
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import org.json.JSONObject
import java.util.HashMap

class FavoritesAdapter(private var list: List<MyFavoriteItem>, private val context: Context) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    lateinit var favt: MyFavoriteItem
    var sp = SharedPreference(context)

    var isFavt = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val product = list[position].product
        holder.name.text = product.name
        holder.price.text = "$" + product.price
        holder.store.text = product.store
        holder.img.placeImage(Constant.BASE_URL + product.img.img1)

        val action = FavoriteFragmentDirections.favtToProductDesc(
            ProductDetails(
                list[position].product.store,
                list[position].product.img.img0,
                list[position].product.name,
                list[position].product.price,
                list[position].product.rating,
                list[position].product.description,
                listOf(
                    list[position].product.img.img0,
                    list[position].product.img.img1,
                    list[position].product.img.img2,
                    list[position].product.img.img3,
                    list[position].product.img.img4,
                    list[position].product.img.img5,
                    list[position].product.img.img6,
                    list[position].product.img.img7,
                ),
                list[position]._id,

                )
        )


        /*  holder.img.setOnClickListener {
              Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                  R.id.action_favtFragment_to_ProductDesc
              )
          }
  */
        holder.container.setOnClickListener(
            Navigation.createNavigateOnClickListener(action)
//            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
//                R.id.action_homeFragment2_to_productsDescriptionFragment
//            )
        )
/*
        holder.container.setOnClickListener{
            Navigation.createNavigateOnClickListener(action)

            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()

        }
        holder.itemView.setOnClickListener {

            Toast.makeText(context, "ok2", Toast.LENGTH_SHORT).show()
            Navigation.createNavigateOnClickListener(action)
        }*/

        if (sp.get(product._id)?.isNotEmpty() == true) {
            isFavt = true
            holder.ivFavt?.setImageResource(R.drawable.ic_heart_red)
        } else {
            isFavt = false
            holder.ivFavt.setImageResource(R.drawable.ic_heart)
        }



        holder.ivFavt.setOnClickListener {

            if (isFavt) {
                holder?.ivFavt?.setImageResource(R.drawable.ic_heart)
            } else {
                holder?.ivFavt?.setImageResource(R.drawable.ic_heart_red)

            }

            updateFavt(holder, product, isFavt)
        }


        holder.ratingbar.rating = product.rating.toFloat()

    }


    private fun updateFavt(holder: ViewHolder, p: ProductX, isFavt: Boolean) {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null
        var sharedPreference = SharedPreference(context)


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/favorite"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                if (isFavt) {
                    sharedPreference.save(p._id, "")
                    Toast.makeText(context, "removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "added to favorites", Toast.LENGTH_SHORT).show()
                    sharedPreference.save(p._id, p._id)
                }

            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "error:" + error.toString(), Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["product"] = p._id
                return params
            }
        }

        mRequestQueue?.add<String>(mStringRequest)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvTitle)
        var store: TextView = itemView.findViewById(R.id.tvStore)
        var price: TextView = itemView.findViewById(R.id.tvPrice)
        var img: ImageView = itemView.findViewById(R.id.img)
        var ivFavt: ImageView = itemView.findViewById(R.id.iv_favt)
        var container: ConstraintLayout = itemView.findViewById(R.id.lyContainer)
        var ratingbar: AppCompatRatingBar = itemView.findViewById(R.id.rating)
    }
}