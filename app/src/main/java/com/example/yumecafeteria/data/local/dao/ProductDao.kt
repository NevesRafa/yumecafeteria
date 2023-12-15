package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Insert
    fun save(order: Product)

    @Delete
    fun remove(order: Product)

    @Update
    fun update(order: Product)

}