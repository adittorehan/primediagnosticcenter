package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "admin_status")
    var admin_status: Boolean = false

)
