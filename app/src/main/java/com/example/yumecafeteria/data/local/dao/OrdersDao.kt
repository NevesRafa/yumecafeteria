package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.local.entity.OrderEntity

@Dao
interface OrdersDao {

    @Query("SELECT * FROM OrderEntity")
    suspend fun getAll(): List<OrderEntity>

    @Insert
    suspend fun save(order: OrderEntity): Long

    @Delete
    suspend fun remove(order: OrderEntity)

    @Update
    suspend fun update(order: OrderEntity)
}