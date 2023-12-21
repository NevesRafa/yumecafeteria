package com.example.yumecafeteria.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductOrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productId: Long,
    val orderId: Long,
    val quantity: Int
)
