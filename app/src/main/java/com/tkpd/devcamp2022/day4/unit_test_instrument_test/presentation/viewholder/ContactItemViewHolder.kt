package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.databinding.ItemContactCardBinding
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

class ContactItemViewHolder(
    private val binding: ItemContactCardBinding,
    private val listener: ContactItemListener? = null,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        binding.run {
            tvContactInitial.text = contact.name[0].toString()
            tvContactName.text = contact.name
            tvContactNumber.text = contact.number

            root.setOnClickListener {
                listener?.onClickContact(contact)
            }
        }
    }

    interface ContactItemListener {
        fun onClickContact(contact: Contact)
    }

    companion object {
        fun create(parent: ViewGroup, listener: ContactItemListener): ContactItemViewHolder {
            val binding = ItemContactCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ContactItemViewHolder(binding, listener)
        }
    }
}