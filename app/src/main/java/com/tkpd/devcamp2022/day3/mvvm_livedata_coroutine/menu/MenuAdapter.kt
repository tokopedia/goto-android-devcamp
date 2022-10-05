package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class MenuAdapter : ListAdapter<Menu, MenuViewHolder>(
    object : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MenuViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val btnMenu = itemView.findViewById<Button>(R.id.btn_menu)

    fun bind(menu: Menu) {
        btnMenu.text = menu.title

        btnMenu.setOnClickListener {
            menu.onClicked()
        }
    }

    companion object {

        fun create(parent: ViewGroup): MenuViewHolder {
            return MenuViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_mvvm_menu, parent, false)
            )
        }
    }
}