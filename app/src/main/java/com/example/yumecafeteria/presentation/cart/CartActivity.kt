package com.example.yumecafeteria.presentation.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    private lateinit var adapter: CartAdapter

    companion object {
        const val PRODUCT_ID = "extra.product.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProductOrderList()

        intent.getParcelableExtra<Product>(PRODUCT_ID)?.let {

        }
    }

    private fun setupProductOrderList() {
        binding.recyclerviewCartProduct.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter {}
        binding.recyclerviewCartProduct.adapter = adapter
    }
}