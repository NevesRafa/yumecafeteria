package com.example.yumecafeteria.presentation.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumecafeteria.data.model.Order
import com.example.yumecafeteria.databinding.ActivityMyOrdersBinding
import org.koin.android.ext.android.inject

class MyOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    private val viewModel: MyOrdersViewModel by inject()
    private lateinit var adapter: MyOrdersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOrderList()
        setupViewModel()

        viewModel.getOrderList()
    }

    private fun setupViewModel() {
        viewModel.loadStateLiveData.observe(this) { state ->
            when (state) {
                is MyOrdersState.Loading -> {}
                is MyOrdersState.Success -> {
                    showResponse(state.result)
                }

                is MyOrdersState.Error -> {}
            }
        }
    }

    private fun showResponse(result: List<Order>) {
        adapter.update(result)
    }

    private fun setupOrderList() {
        binding.recyclerviewOrders.layoutManager = LinearLayoutManager(this)
        adapter = MyOrdersAdapter()
        binding.recyclerviewOrders.adapter = adapter
    }
}