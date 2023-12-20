package com.example.yumecafeteria.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productName: String,
    val description: String,
    val price: Double
) : Parcelable
