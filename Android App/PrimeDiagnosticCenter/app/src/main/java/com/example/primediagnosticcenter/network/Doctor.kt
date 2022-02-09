package com.example.primediagnosticcenter.network

import com.squareup.moshi.Json


data class Doctor(
    var id: Int = 0,
    var name: String = "",
    var designation: String = "",
    var department: String = "",
    val photo: String
)

