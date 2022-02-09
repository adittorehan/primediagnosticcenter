package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    var doctorId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "designation")
    var designation: String = "",

    @ColumnInfo(name = "department")
    var department: String = ""
)
