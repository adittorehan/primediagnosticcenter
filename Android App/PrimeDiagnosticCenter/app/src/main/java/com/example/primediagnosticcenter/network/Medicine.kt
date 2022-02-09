package com.example.primediagnosticcenter.network

data class Medicine(
    var id: Int = 0,
    var name: String = "",
    var detail: String = "",
    var price: Double,
    var quantity: Int,
    val photo: String
)
