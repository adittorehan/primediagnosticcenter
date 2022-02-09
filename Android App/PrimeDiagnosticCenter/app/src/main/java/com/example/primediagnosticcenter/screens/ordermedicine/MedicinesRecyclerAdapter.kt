package com.example.primediagnosticcenter.screens.ordermedicine


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.Medicine
import com.example.primediagnosticcenter.screens.admin.MedicinesFragmentDirections

class MedicinesRecyclerAdapter(val context: Context, var cart: ArrayList<Pair<Long, Int>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var medicines = arrayListOf<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.medicine_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MedicineViewHolder -> {
                holder.bind(medicines.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return medicines.size
    }

    inner class MedicineViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            val btn = itemView.findViewById<Button>(R.id.add_to_cart_button)
            btn.setOnClickListener { onClick(it) }
        }

        var id: Long = 0
        val name = itemView.findViewById<TextView>(R.id.medicine_name_textView)
        val details = itemView.findViewById<TextView>(R.id.medicine_detail_textView)
        val price = itemView.findViewById<TextView>(R.id.medicine_price_textView)
        val quantity = itemView.findViewById<EditText>(R.id.medicine_quantity)

        fun bind(medicine: Medicine) {
            id = medicine.medicineId
            name.text = medicine.name
            price.text = "${medicine.price}tk"
            details.text = medicine.detail
        }

        override fun onClick(v: View?) {

            val num = quantity.text.toString().toInt()
            if(num > 0 ){
                v?.isEnabled = false
                cart.add(Pair(id,num))
                Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Failed to add to Cart", Toast.LENGTH_SHORT).show()
            }

        }
    }


}
