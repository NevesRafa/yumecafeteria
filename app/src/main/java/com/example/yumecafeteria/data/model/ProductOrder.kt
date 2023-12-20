package com.example.yumecafeteria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductOrder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Long,
    val orderId: Long,
    val quantity: Int,
)
