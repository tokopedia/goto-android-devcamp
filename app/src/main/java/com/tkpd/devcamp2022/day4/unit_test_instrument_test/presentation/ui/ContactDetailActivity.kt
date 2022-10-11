package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.databinding.ActivityContactDetailBinding
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact

class ContactDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val extras = intent.extras
        extras?.run {
            val contactName = getString(EXTRA_CONTACT_NAME, "")
            val contactPhoneNumber = getString(EXTRA_CONTACT_PHONE_NUMBER, "")

            binding.tvContactDetailName.text = contactName
            binding.tvContactDetailPhoneNumber.text = contactPhoneNumber
        }

    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_PHONE_NUMBER = "EXTRA_CONTACT_PHONE_NUMBER"

        fun createInstance(
            context: Context,
            contact: Contact,
        ): Intent {
            val intent = Intent(context, ContactDetailActivity::class.java)
            val extras = Bundle().apply {
                putString(EXTRA_CONTACT_NAME, contact.name)
                putString(EXTRA_CONTACT_PHONE_NUMBER, contact.number)
            }
            intent.putExtras(extras)
            return intent
        }
    }
}