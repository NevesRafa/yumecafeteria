package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.model.ProductOrder

@Dao
interface ProductOrderDao {

    @Query("SELECT * FROM productOrder")
    suspend fun getAll(): List<ProductOrder>

    @Insert
    suspend fun save(order: ProductOrder)

    @Delete
    suspend fun remove(order: ProductOrder)

    @Update
    suspend fun update(order: ProductOrder)
}