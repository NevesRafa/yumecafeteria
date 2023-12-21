package com.example.yumecafeteria.presentation.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.data.model.Order
import com.example.yumecafeteria.databinding.ItemHistoryOrderBinding
import com.example.yumecafeteria.internal.extension.formatAsCurrency
import com.example.yumecafeteria.internal.extension.formatAsCustomUnit


class MyOrdersAdapter : RecyclerView.Adapter<OrdersListViewHolder>() {

    private val dataset = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryOrderBinding.inflate(inflater, parent, false)
        return OrdersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size

    fun update(list: List<Order>) {
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class OrdersListViewHolder(
    private val binding: ItemHistoryOrderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(order: Order) {
        binding.orderNumber.text = "Pedido concluído - N°${order.id}"
        binding.orderQuantityTotal.text = order.totalProcutsCount.formatAsCustomUnit()
        binding.orderTotalValue.text = order.totalPrice.formatAsCurrency()

        val productsText = StringBuilder()

        order.products.forEach { product ->
            val productText = "${product.quantity}x\t${product.product.productName}\t\t${product.product.price.formatAsCurrency()}"
            productsText.append(productText)

            if (order.products.last() != product) {
                productsText.append("\n")
            }
        }

        binding.orderItems.text = productsText.toString()
    }
}