package com.example.primediagnosticcenter.screens.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.Medicine

class MedicinesRecyclerAdapter(
    private val navController: NavController, private var inID: Int = 100001
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var medicines = arrayListOf<Medicine>()
    private lateinit var action: NavDirections
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.medicine_admin_view, parent, false)
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
            itemView.setOnClickListener { onClick(it) }
            itemView.id = inID++
        }

        var id: Long = 0
        val name = itemView.findViewById<TextView>(R.id.medicine_name_text)
        val details = itemView.findViewById<TextView>(R.id.medicine_detail)
        val price = itemView.findViewById<TextView>(R.id.medicine_price_text)

        fun bind(medicine: Medicine) {
            id = medicine.medicineId
            name.text = medicine.name
            price.text = "${medicine.price}tk"
            details.text = medicine.detail

        }

        override fun onClick(v: View?) {
            println("ID: $id")
            action =
                MedicinesFragmentDirections.actionMedicinesFragmentToEditMedicineFragment(id)

            navController.navigate(action)
        }
    }


}
