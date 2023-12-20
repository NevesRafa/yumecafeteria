package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.model.Orders

@Dao
interface OrdersDao {

    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<Orders>

    @Insert
    suspend fun save(order: Orders): Long

    @Delete
    suspend fun remove(order: Orders)

    @Update
    suspend fun update(order: Orders)
}