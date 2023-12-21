package com.example.yumecafeteria.presentation.orders

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.data.model.ProductOrder
import com.example.yumecafeteria.databinding.ItemHistoryOrderBinding
import com.example.yumecafeteria.internal.extension.formatAsCustomUnit


class MyOrdersAdapter() : RecyclerView.Adapter<OrdersListViewHolder>() {

    private val dataset = mutableListOf<ProductOrder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryOrderBinding.inflate(inflater, parent, false)
        return OrdersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size

    fun update(list: List<ProductOrder>) {
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class OrdersListViewHolder(private val binding: ItemHistoryOrderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productOrder: ProductOrder) {

        binding.orderNumber.text = "Pedido concluído - N°${productOrder.orderId}"
        binding.orderQuantityTotal.text = productOrder.quantity.formatAsCustomUnit()

        val content = SpannableString("Content")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.more.text = content

    }
}