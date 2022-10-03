package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.devcamp2022.databinding.FragmentContactBookBinding
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.contact.ContactDataSourceImpl
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.data.model.Contact
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.adapter.ContactBookAdapter
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewholder.ContactItemViewHolder
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewmodel.ContactBookViewModel2

class ContactBookFragment: Fragment(), ContactItemViewHolder.ContactItemListener {

    lateinit var viewModel: ContactBookViewModel2

    private var binding: FragmentContactBookBinding? = null
    private var contactBookAdapter: ContactBookAdapter? = null

    private val permissions = arrayListOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBookBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
        getContactList()
    }

    private fun initObserver() {
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            contactBookAdapter?.setContacts(contacts)
        }
    }

    private fun getContactList() {
        checkPermission {
            viewModel.getContactList()
//            viewModel.getDelayedContactList()
        }
    }

    private fun initViewModel() {
        val contactDataSource = ContactDataSourceImpl(context?.contentResolver)
        val viewModelProvider = ViewModelProvider(
            this, ContactBookViewModel2.getFactory(contactDataSource))
        viewModel = viewModelProvider.get(ContactBookViewModel2::class.java)
    }

    private fun initRecyclerView() {
        contactBookAdapter = ContactBookAdapter(emptyList(), this)
        binding?.rvContactBookItems?.run {
            adapter = contactBookAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClickContact(contact: Contact) {
        context?.let {
            val intent = ContactDetailActivity.createInstance(it, contact)
            startActivity(intent)
        } ?: run {
            Toast.makeText(
                context,
                "empty context",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun checkPermission(action: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.forEach { permission ->
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        permission
                    ) == PackageManager.PERMISSION_GRANTED) {
                    action.invoke()
                } else {
                    requestPermission()
                    return@forEach
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            ),
            CONTACT_PERMISSION_CODE
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
                getContactList()
            } else {
                Toast.makeText(context, "Permission missing", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val CONTACT_PERMISSION_CODE = 123
    }
}