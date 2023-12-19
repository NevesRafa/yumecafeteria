package com.example.yumecafeteria.presentation.description

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.databinding.ActivityDescriptionBinding
import org.koin.android.ext.android.inject

class DescriptionActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_DESCRIPTION = "extra.product.description"
    }

    private val viewModel: DescriptionViewModel by inject()

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

        addProductToCart(product.id)
    }

    private fun addProductToCart(productId: Int) {
        binding.fabAddCart.setOnClickListener {
            viewModel.loadProductCart(productId)
            Toast.makeText(this, "Produto adicionado ao carrinho com sucesso!!!", Toast.LENGTH_SHORT).show()
        }

    }
}