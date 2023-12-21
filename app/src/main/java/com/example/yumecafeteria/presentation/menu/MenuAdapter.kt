package com.example.yumecafeteria.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.local.entity.ProductEntity
import com.example.yumecafeteria.databinding.ItemProductBinding
import com.example.yumecafeteria.internal.extension.formatAsCurrency

class MenuAdapter(private val onProductClick: (ProductEntity) -> Unit) : RecyclerView.Adapter<ProductListViewHolder>() {

    private val dataset = mutableListOf<ProductEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(dataset[position], onProductClick)
    }

    override fun getItemCount() = dataset.size

    fun addProductList(list: List<ProductEntity>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class ProductListViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity, onProductClick: (ProductEntity) -> Unit) {

        binding.root.setOnClickListener {
            onProductClick(product)
        }

        binding.productName.text = product.productName
        binding.productDescription.text = product.description
        binding.productPrice.text = product.price.formatAsCurrency()
        binding.image.setImageResource(R.drawable.cappucino)
    }
}
