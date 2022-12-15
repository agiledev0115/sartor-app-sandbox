package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.CartItem
import com.sartor.data.model.ShoppingBagItem
import com.sartor.util.constants.Constant
import com.sartor.util.constants.placeImage

class ShoppingBagItemAdapter(val list: List<CartItem>, val context: Context) :
    RecyclerView.Adapter<ShoppingBagItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_shopping_bag, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var orderQuantity = list[position].productQuantity

        holder.name.text = list[position].productName
        holder.desc.text = list[position].productDescription
        holder.price.text = Constant.CURRENCY+list[position].productPrice
        holder.quantity.text = list[position].productQuantity.toString()

         //Todo -> Image received from server is wrong using brand image, REVERT LATER

         val BASE_URL = "http://52.159.111.122:8082"
        holder.img.placeImage(BASE_URL + list[position].productImage)
        //Log.i("CARTIMAGE",Constant.BASE_URL + list[position].productImage)

        holder.img.setOnClickListener {
            Navigation.findNavController(this.context as Activity, R.id.homeHost).navigate(
                R.id.action_yourShoppingBagFragment_to_shippingInformationFragment
            )
        }

        holder.container.setOnClickListener {
            Navigation.findNavController(this.context as Activity, R.id.homeHost).navigate(
                R.id.action_yourShoppingBagFragment_to_shippingInformationFragment
            )
        }

        holder.increase.setOnClickListener {
            orderQuantity += 1
            holder.quantity.text = orderQuantity.toString()
        }

        holder.decrease.setOnClickListener {
            if (orderQuantity == 0) {
                holder.quantity.text = orderQuantity.toString()
            } else {
                orderQuantity -= 1
                holder.quantity.text = orderQuantity.toString()
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvTitle)
        var price: TextView = itemView.findViewById(R.id.tvCost)
        var desc: TextView = itemView.findViewById(R.id.tvDescription)
        var quantity: TextView = itemView.findViewById(R.id.tvOrderNo)
        var img: ImageView = itemView.findViewById(R.id.ivCardImg)
        var container: ConstraintLayout = itemView.findViewById(R.id.container)
        var increase: ImageButton = itemView.findViewById(R.id.btnIncrease)
        var decrease: ImageButton = itemView.findViewById(R.id.btnDecrease)
    }
}