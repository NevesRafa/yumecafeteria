package com.example.yumecafeteria.presentation.cart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.databinding.ActivityCartBinding
import com.example.yumecafeteria.presentation.description.DescriptionActivity
import org.koin.android.ext.android.inject

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by inject()
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProductOrderList()
        setupViewModel()

        viewModel.getProductCartList()
        viewModel.updateQuantityTotal()
    }

    private fun setupViewModel() {
        viewModel.loadStateLiveData.observe(this) { state ->
            when (state) {
                is CartState.Loading -> {}
                is CartState.Success -> showResponse(state.result)
                is CartState.Error -> {}
                is CartState.Remove -> removeProductFromList(state.product)
                is CartState.UpdateTotalQuantity -> totalQuantity(state.total)
            }
        }
    }

    private fun showResponse(result: List<ProductCart>) {
        adapter.update(result)
    }

    private fun setupProductOrderList() {
        binding.recyclerviewCartProduct.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter(
            onProductClick = { product ->
                val intent = Intent(this, DescriptionActivity::class.java)
                intent.putExtra(DescriptionActivity.EXTRA_PRODUCT_DESCRIPTION, product)
                startActivity(intent)
            },
            onDeleteClick = {
                removeProductFromList(it)
            }
        )
        binding.recyclerviewCartProduct.adapter = adapter
    }

    private fun removeProductFromList(productCart: ProductCart) {
        viewModel.removeProduct(productCart)
    }

    private fun totalQuantity(total: Int) {
        binding.quantityTotal.text = total.toString()
    }

}