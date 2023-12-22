package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.local.entity.ProductOrderEntity

@Dao
interface ProductOrderDao {
    @Insert
    suspend fun save(order: ProductOrderEntity)

    @Delete
    suspend fun remove(order: ProductOrderEntity)

    @Update
    suspend fun update(order: ProductOrderEntity)

    @Query("SELECT * FROM ProductOrderEntity WHERE orderId = :orderId")
    suspend fun getAllByOrder(orderId: Long): List<ProductOrderEntity>
}