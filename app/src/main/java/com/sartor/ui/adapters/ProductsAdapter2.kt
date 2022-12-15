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
import com.sartor.data.model.Product
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage
import java.util.HashMap

class ProductsAdapter2(private var list: List<Product>, private val context: Context) :
    RecyclerView.Adapter<ProductsAdapter2.ViewHolder>() {
    var sp = SharedPreference(context)

    var isFavt = false;

    lateinit var product: Product

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_home_screen, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        product = list[position]
        holder.name.text = list[position].name
        holder.price.text = "$" + list[position].price
        holder.store.text = list[position].store
        holder.img.placeImage(Constant.BASE_URL + list[position].img)



        Log.d("_id", product.id)
        holder.img.setOnClickListener {
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                R.id.action_sellerProfileFragment_to_yourShoppingBagFragment
            )
        }

        holder.container.setOnClickListener {
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                R.id.action_sellerProfileFragment_to_yourShoppingBagFragment
            )
        }


        if (sp.get(product.id)?.isNotEmpty() == true) {
            isFavt=true
            holder?.ivFavt?.setImageResource(R.drawable.ic_heart_red)
        } else {
            isFavt=false
            holder?.ivFavt?.setImageResource(R.drawable.ic_heart)
        }

        holder.ivFavt.setOnClickListener {
            if (isFavt) {
                holder?.ivFavt?.setImageResource(R.drawable.ic_heart)
            } else {
                holder?.ivFavt?.setImageResource(R.drawable.ic_heart_red)

            }

            updateFavt(holder, product, isFavt)
        }
    }


    private fun updateFavt(holder: ViewHolder, p: Product, isFavt: Boolean) {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null
        var sharedPreference = SharedPreference(context)

        var id = p.id
        Log.d("_productId", p.id)


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/favorite"
        mRequestQueue = Volley.newRequestQueue(context)
        mStringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->

                if (isFavt) {
                    sharedPreference.save(product.id, "")
                    Toast.makeText(context, "removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "added to favorites", Toast.LENGTH_SHORT).show()
                    sharedPreference.save(product.id, product.id)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "error:" + error.message, Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


            override fun getParams(): MutableMap<String, String> {

                val params: MutableMap<String, String> = HashMap()
                params["product"] = p.id
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
    }
}