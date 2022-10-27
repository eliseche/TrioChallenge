package com.eliseche.triochallenge.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eliseche.triochallenge.data.models.Item
import com.eliseche.triochallenge.databinding.ItemRestaurantMenuBinding

class RestaurantMenuAdapter(
    private val onEvent: (RestaurantEvent) -> Unit
) : RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolder>() {
    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            Glide
                .with(holder.itemView.context)
                .load(item.url)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(logo)

            title.text = item.name

            itemMenu.setOnClickListener {
                onEvent(RestaurantEvent.OnMenuClick(item))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Item>) {
        this.items = items
    }

    inner class ViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)
}