package com.example.yumecafeteria.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yumecafeteria.R
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.databinding.ItemProductCartBinding

class CartAdapter(
    private val onProductClick: (Product) -> Unit,
    private val onDeleteClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductCartListViewHolder>() {

    private val dataset = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCartListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductCartBinding.inflate(inflater, parent, false)
        return ProductCartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductCartListViewHolder, position: Int) {
        holder.bind(
            dataset[position],
            onProductClick,
            onDeleteClick
        )
    }

    override fun getItemCount() = dataset.size

    fun update(list: List<Product>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class ProductCartListViewHolder(
    private val binding: ItemProductCartBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        product: Product,
        onProductClick: (Product) -> Unit,
        onDeleteClick: (Product) -> Unit
    ) {

        var quantity = 1

        binding.root.setOnClickListener {
            onProductClick(product)
        }

        binding.btnDelete.setOnClickListener {
            onDeleteClick(product)
        }

        binding.btnIcUp.setOnClickListener {
            quantity++
            binding.quantity.text = quantity.toString()
        }

        binding.btnIcDown.setOnClickListener {
            if (quantity != 1) {
                quantity--
                binding.quantity.text = quantity.toString()
            } else {
                return@setOnClickListener
            }

        }

        binding.productCartName.text = product.productName
        binding.productCartDescription.text = product.description
        binding.productCartPrice.text = "R$ ${product.price}"
        binding.image.setImageResource(R.drawable.cappucino)
        binding.quantity.text = quantity.toString()
    }
}