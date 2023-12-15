package com.example.yumecafeteria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Orders(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)