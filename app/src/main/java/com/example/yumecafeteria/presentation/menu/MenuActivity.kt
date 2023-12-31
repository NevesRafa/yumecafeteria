package com.example.yumecafeteria.presentation.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yumecafeteria.data.local.entity.ProductEntity
import com.example.yumecafeteria.databinding.ActivityMenuBinding
import com.example.yumecafeteria.presentation.cart.CartActivity
import com.example.yumecafeteria.presentation.description.DescriptionActivity
import org.koin.android.ext.android.inject

class MenuActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel by inject()

    private lateinit var binding: ActivityMenuBinding
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupList()
        openMyCart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProductsList()
    }

    private fun setupViewModel() {
        viewModel.loadStateLiveData.observe(this) { state ->
            when (state) {
                is MenuState.Loading -> showLoading()
                is MenuState.Success -> {
                    hideLoading()
                    showResponse(state.result)
                    setCartQuantity(state.cartQuantity)
                }

                is MenuState.Error -> {}
            }
        }
    }

    private fun showResponse(result: List<ProductEntity>) {
        adapter.addProductList(result)
    }

    private fun showLoading() {
        binding.recyclerviewProducts.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
        binding.recyclerviewProducts.visibility = View.VISIBLE
    }

    private fun setupList() {
        adapter = MenuAdapter { product ->
            val intent = Intent(this, DescriptionActivity::class.java)
            intent.putExtra(DescriptionActivity.EXTRA_PRODUCT_DESCRIPTION, product)
            startActivity(intent)
        }

        binding.recyclerviewProducts.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MenuActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = this@MenuActivity.adapter
        }
    }

    private fun openMyCart() {
        binding.btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCartQuantity(cartQuantity: Int) {
        if (cartQuantity > 0) {
            binding.badgeGroup.visibility = View.VISIBLE
            binding.badgeText.text = cartQuantity.toString()
        } else {
            binding.badgeGroup.visibility = View.GONE
        }
    }
}