package com.example.yumecafeteria.presentation.cart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivitySuccessfulPurchaseBinding
import com.example.yumecafeteria.presentation.home.HomeActivity

class SuccessfulPurchaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessfulPurchaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessfulPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backToHome()
    }

    private fun backToHome() {
        binding.btnBackToMenu.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}