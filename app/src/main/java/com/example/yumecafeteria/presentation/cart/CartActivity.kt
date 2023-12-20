package com.example.yumecafeteria.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.databinding.ActivityCartBinding
import com.example.yumecafeteria.internal.extension.formatAsCurrency
import com.example.yumecafeteria.internal.extension.formatAsCustomUnit
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
        createOrder()

        viewModel.getProductCartList()
    }

    private fun setupViewModel() {
        viewModel.loadStateLiveData.observe(this) { state ->
            when (state) {
                is CartState.Loading -> {}
                is CartState.Success -> {
                    showResponse(state.cartProductList)
                    setTotalQuantity(state.totalQuantity)
                    setTotalPrice(state.totalPrice)
                }

                is CartState.Error -> {}
            }
        }
    }

    private fun showResponse(result: List<ProductCart>) {
        if (result.isEmpty()) {
            binding.recyclerviewCartProduct.visibility = View.GONE
            binding.emptyCartMessage.visibility = View.VISIBLE
        } else {
            binding.recyclerviewCartProduct.visibility = View.VISIBLE
            binding.emptyCartMessage.visibility = View.GONE
        }
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
            },
            onUpClick = {
                it.quantity++
            },
            onDownClick = {
                it.quantity--
            }
        )
        binding.recyclerviewCartProduct.adapter = adapter
    }

    private fun removeProductFromList(productCart: ProductCart) {
        viewModel.removeProduct(productCart)
    }

    private fun setTotalQuantity(quantity: Int) {
        binding.quantityTotal.text = quantity.formatAsCustomUnit("Unid")
    }

    private fun setTotalPrice(total: Double) {
        binding.total.text = total.formatAsCurrency()
    }

    private fun createOrder() {


        binding.fabFinalizePurchase.setOnClickListener {
            viewModel.createOrder()
            Toast.makeText(this, "Compra concluida com sucesso!!!", Toast.LENGTH_SHORT).show()
        }
    }


}