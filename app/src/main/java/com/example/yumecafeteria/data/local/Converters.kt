package com.example.yumecafeteria.data.local

import androidx.room.TypeConverter
import com.example.yumecafeteria.data.model.Orders
import com.example.yumecafeteria.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    //Product
    @TypeConverter
    fun jsonToListProduct(input: String): List<Product> {
        return gson.fromJson(input, object : TypeToken<List<Product>>() {}.type)
    }

    @TypeConverter
    fun listProductToJson(input: List<Product>): String {
        return gson.toJson(input)
    }

    // Orders
    @TypeConverter
    fun jsonToListOrders(input: String): List<Orders> {
        return gson.fromJson(input, object : TypeToken<List<Orders>>() {}.type)
    }

    @TypeConverter
    fun listOrdersToJson(input: List<Orders>): String {
        return gson.toJson(input)
    }
}