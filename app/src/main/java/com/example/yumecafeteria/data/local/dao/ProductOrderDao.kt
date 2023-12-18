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
    fun getAll(): List<ProductOrder>

    @Insert
    fun save(order: ProductOrder)

    @Delete
    fun remove(order: ProductOrder)

    @Update
    fun update(order: ProductOrder)
}