package com.example.yumecafeteria.presentation.description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_DESCRIPTION = "extra.product.description"
    }

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Product>(EXTRA_PRODUCT_DESCRIPTION)?.let {
            loadDescription(it)
        }
    }

    private fun loadDescription(product: Product) {
        binding.productDescriptionImage.setImageResource(R.drawable.cappucino)
        binding.productDescription.text = product.description
        binding.productDescriptionName.text = product.productName
        binding.productDescriptionPrice.text = "R$ ${product.price}"
    }
}