package com.example.yumecafeteria.presentation.cart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivitySuccessfulPurchaseBinding
import com.example.yumecafeteria.presentation.menu.MenuActivity
import com.example.yumecafeteria.presentation.orders.MyOrdersActivity

class SuccessfulPurchaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessfulPurchaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessfulPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backToMenu()
        openMyOrders()

    }

    private fun backToMenu() {
        binding.btnBackToMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun openMyOrders() {
        binding.btnMyOrders.setOnClickListener {
            val intent = Intent(this, MyOrdersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}