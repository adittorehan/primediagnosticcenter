package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    var medicineId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "detail")
    var detail: String = "",

    @ColumnInfo(name = "price")
    var price: Double = 0.0
)
