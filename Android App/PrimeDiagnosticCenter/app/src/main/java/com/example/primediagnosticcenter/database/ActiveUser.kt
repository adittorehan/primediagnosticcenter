package com.example.primediagnosticcenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "active_user")
data class ActiveUser(
    @PrimaryKey(autoGenerate = true)
    var user_id: Long = 0L,

    @ColumnInfo
    var active_status: Boolean = false
)
