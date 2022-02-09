package com.example.primediagnosticcenter.network

data class ManageAppointment(
    var id: Int,
    var doctor: Int,
    var user_email: String,
    var user_phone: String,
    var date: String,
    var status: Int,
)
