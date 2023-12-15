package com.example.yumecafeteria.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yumecafeteria.data.local.Converters
import com.example.yumecafeteria.data.local.dao.OrdersDao
import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.local.dao.ProductOrderDao
import com.example.yumecafeteria.data.model.Orders
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.data.model.ProductOrder

@Database(
    entities = [
        ProductOrder::class,
        Orders::class,
        Product::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productOrderDao(): ProductOrderDao
    abstract fun productDao(): ProductDao
    abstract fun ordersDao(): OrdersDao
}