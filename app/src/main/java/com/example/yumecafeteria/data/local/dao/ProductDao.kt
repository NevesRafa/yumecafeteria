package com.example.yumecafeteria.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yumecafeteria.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    suspend fun getAll(): List<ProductEntity>

    @Insert
    suspend fun save(productList: List<ProductEntity>)

    @Delete
    suspend fun remove(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    suspend fun searchId(id: Long): ProductEntity

}