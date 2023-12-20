package com.example.yumecafeteria.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivityHomeBinding
import com.example.yumecafeteria.presentation.menu.MenuActivity
import com.example.yumecafeteria.presentation.orders.MyOrdersActivity
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openMenu()
        openMyOrders()
        ensureProductList()
    }

    private fun openMenu() {
        binding.btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openMyOrders() {
        binding.btnOrders.setOnClickListener {
            val intent = Intent(this, MyOrdersActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ensureProductList() {
        val productList = viewModel.getProductList()
        if (productList.isEmpty()) {
            viewModel.saveMockListProduct()
        }
    }
}