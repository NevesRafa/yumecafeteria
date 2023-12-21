package com.example.yumecafeteria.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productName: String,
    val description: String,
    val price: Double
) : Parcelable
