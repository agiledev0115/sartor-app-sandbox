package com.sartor.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sartor.R
import com.sartor.data.model.Order

class CheckoutAdapter(val list: List<Order>, val context: Context) : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.child_checkout, parent, false)
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.quantity.text = list[position].quantity.toString()
        holder.price.text = list[position].price.toString()

        holder.itemView.setOnClickListener {
            Navigation.findNavController(context as Activity, R.id.homeHost).navigate(
                R.id.action_checkoutFragment_to_shippingInformationFragment
            )
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvOrderName)
        var quantity: TextView = itemView.findViewById(R.id.tvQuantity)
        var price: TextView = itemView.findViewById(R.id.tvPrice)
    }
}