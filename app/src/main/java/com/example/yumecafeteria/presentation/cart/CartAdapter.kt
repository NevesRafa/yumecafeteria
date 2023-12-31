package com.example.yumecafeteria.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.local.entity.ProductEntity
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.databinding.ItemProductCartBinding
import com.example.yumecafeteria.internal.extension.formatAsCurrency

class CartAdapter(
    private val onProductClick: (ProductEntity) -> Unit,
    private val onDeleteClick: (ProductCart) -> Unit,
    private val onUpClick: (ProductCart) -> Unit,
    private val onDownClick: (ProductCart) -> Unit,
) : RecyclerView.Adapter<ProductCartListViewHolder>() {

    private val dataset = mutableListOf<ProductCart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCartListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductCartBinding.inflate(inflater, parent, false)
        return ProductCartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductCartListViewHolder, position: Int) {
        holder.bind(
            dataset[position],
            onProductClick,
            onDeleteClick,
            onUpClick,
            onDownClick
        )
    }

    override fun getItemCount() = dataset.size

    fun update(list: List<ProductCart>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class ProductCartListViewHolder(
    private val binding: ItemProductCartBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        productCart: ProductCart,
        onProductClick: (ProductEntity) -> Unit,
        onDeleteClick: (ProductCart) -> Unit,
        onUpClick: (ProductCart) -> Unit,
        onDownClick: (ProductCart) -> Unit
    ) {

        binding.productCartDescription.setOnClickListener {
            onProductClick(productCart.product)
        }

        binding.btnDelete.setOnClickListener {
            onDeleteClick(productCart)
        }

        binding.btnIcUp.setOnClickListener {
            onUpClick(productCart)
            binding.quantity.text = productCart.quantity.toString()
        }

        binding.btnIcDown.setOnClickListener {
            onDownClick(productCart)
            binding.quantity.text = productCart.quantity.toString()
        }

        binding.productCartName.text = productCart.product.productName
        binding.productCartDescription.text = productCart.product.description
        binding.productCartPrice.text = productCart.product.price.formatAsCurrency()
        binding.image.setImageResource(R.drawable.cappucino)
        binding.quantity.text = productCart.quantity.toString()
    }
}
