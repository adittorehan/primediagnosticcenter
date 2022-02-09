package com.example.primediagnosticcenter.screens.ordermedicine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MedicineOrder(
    val id: Int,
    val quantity: Int
) : Parcelable {
    override fun toString(): String {
        return "Medicine ID:${id}, Quantity: $quantity"
    }

}