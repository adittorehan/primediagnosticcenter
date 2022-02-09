package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temp_order")
data class TempOrder(
    @PrimaryKey(autoGenerate = true)
    var temporderId: Long = 0,

    @ColumnInfo(name = "medicineId")
    var medicineId: Long,

    @ColumnInfo(name = "quantity")
    var quantity: Int

)
