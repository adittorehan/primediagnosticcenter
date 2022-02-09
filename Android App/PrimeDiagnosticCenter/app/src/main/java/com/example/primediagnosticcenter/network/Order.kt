package com.example.primediagnosticcenter.network

data class Order(
    val id: Int?,
    val email: String,
    val address: String,
    val phone: String,
    var status: Int,
)
