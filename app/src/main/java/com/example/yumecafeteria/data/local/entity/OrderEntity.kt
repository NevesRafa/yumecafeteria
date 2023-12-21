package com.example.yumecafeteria.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)
