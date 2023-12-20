package com.example.yumecafeteria.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivityHomeBinding
import com.example.yumecafeteria.presentation.menu.MenuActivity
import com.example.yumecafeteria.presentation.orders.MyOrdersActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openMenu()
        openMyOrders()
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
}