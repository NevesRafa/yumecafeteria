package com.example.yumecafeteria.presentation.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivityMyOrdersBinding

class MyOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}