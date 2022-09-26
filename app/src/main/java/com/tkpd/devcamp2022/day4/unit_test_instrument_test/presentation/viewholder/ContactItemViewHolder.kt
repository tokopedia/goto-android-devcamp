package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.databinding.ItemContactCardBinding
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

class ContactItemViewHolder(
    private val binding: ItemContactCardBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        binding.run {
            tvContactInitial.text = contact.name[0].toString()
            tvContactName.text = contact.name
            tvContactNumber.text = contact.number
        }
    }
}