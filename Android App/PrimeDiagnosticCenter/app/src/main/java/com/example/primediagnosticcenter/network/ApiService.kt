package com.example.primediagnosticcenter.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL =
    "http://10.0.2.2:8000/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface ApiService {
    @GET("doctors")
    suspend fun getDoctors():
            List<Doctor>

    @GET("blogs")
    suspend fun getBlogs():
            List<Blog>

    @GET("medicines")
    suspend fun getMedicines():
            List<Medicine>

    @GET("doctors/{name}")
    suspend fun getDoctors(@Path(value = "name") name: String):
            List<Doctor>

    @GET("medicines/{name}")
    suspend fun getMedicines(@Path(value = "name") name: String):
            List<Medicine>

    @GET("blogs/{id}")
    suspend fun getBlog(@Path(value = "id") id: Int): Blog

    @GET("users/{email}/{password}")
    suspend fun isAdminUser(
        @Path(value = "email") email: String,
        @Path(value = "password") password: String
    ): IsAdmin


    @POST("appointments")
    suspend fun postAppointment(@Body newAppointment: Appointment): Appointment

    @GET("appointments")
    suspend fun getAppointments(): List<ManageAppointment>

    @PUT("appointments/{id}")
    suspend fun putAppointment(
        @Path(value = "id") id: Int,
        @Body newAppointment: ManageAppointment
    ): ManageAppointment

    @POST("orders")
    suspend fun postOrder(@Body newOrder: Order): Order

    @POST("ordermedicine")
    suspend fun postMedicineOrder(@Body newMedicineOrder: MedicineOrder): MedicineOrder


}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}