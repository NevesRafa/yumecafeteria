package com.example.yumecafeteria.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.databinding.ActivitySplashBinding
import com.example.yumecafeteria.presentation.menu.MenuActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        splashScreenDuration()
    }

    private fun splashScreenDuration() {
        val splashScreenDuration = 4500L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}