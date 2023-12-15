package com.example.yumecafeteria.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.databinding.ItemProductBinding

class MenuAdapter(private val onProductClick: (Product) -> Unit) : RecyclerView.Adapter<ProductListViewHolder>() {

    private val dataset = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(dataset[position], onProductClick)
    }

    override fun getItemCount() = dataset.size

    fun addCharacterList(list: List<Product>) {
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class ProductListViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product, onProductClick: (Product) -> Unit) {

        binding.root.setOnClickListener {
            onProductClick(product)
        }

        binding.productName.text = product.productName
        binding.productDescription.text = product.description
        binding.productPrice.text = "R$ ${product.price}"
        binding.image.setImageResource(R.drawable.cappucino)
    }
}
