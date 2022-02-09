package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Orders(
    @PrimaryKey(autoGenerate = true)
    var order_id: Long = 0L,

    @ColumnInfo(name = "medicineId")
    var medicineId: Long,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "phone")
    var phone: String
)
