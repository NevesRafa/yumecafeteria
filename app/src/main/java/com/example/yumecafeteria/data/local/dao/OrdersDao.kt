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
    fun getAll(): List<Orders>

    @Insert
    fun save(order: Orders)

    @Delete
    fun remove(order: Orders)

    @Update
    fun update(order: Orders)
}