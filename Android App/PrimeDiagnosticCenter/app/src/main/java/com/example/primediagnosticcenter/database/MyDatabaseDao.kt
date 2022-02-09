package com.example.primediagnosticcenter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDatabaseDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE email=:email AND password=:password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    //Medicine
    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicines WHERE medicineId=:medicineId Limit 1")
    suspend fun getMedicine(medicineId: Long): Medicine?

    @Query("SELECT * FROM medicines")
    suspend fun getAllMedicines(): List<Medicine>

    @Query("SELECT * FROM medicines WHERE name LIKE :name")
    suspend fun getSearchMedicines(name: String): List<Medicine>


    // Active User Table
    @Insert
    suspend fun insertUserLog(activeUser: ActiveUser)

    @Query("DELETE FROM active_user")
    suspend fun clearActiveUserLog()

    @Query("SELECT * FROM active_user LIMIT 1")
    suspend fun getUserLog(): ActiveUser?


    // TempOrder
    @Insert
    suspend fun insertTempOrder(tempOrder: TempOrder)

    @Query("DELETE FROM temp_order")
    suspend fun clearTempOrders()

    @Query("SELECT * FROM temp_order")
    suspend fun getTempOrders(): List<TempOrder>


    //Order
    @Insert
    suspend fun insertOrder(orders: Orders)

    @Query("SELECT * FROM orders")
    suspend fun getOrders(): List<Orders>

    @Update
    suspend fun updaterOrder(orders: Orders)


}