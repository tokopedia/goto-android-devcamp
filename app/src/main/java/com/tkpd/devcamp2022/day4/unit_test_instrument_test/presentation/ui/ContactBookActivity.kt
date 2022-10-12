package com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.R

class ContactBookActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragment(savedInstanceState)
        setContentView(R.layout.activity_contact_book)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            inflateFragment()
        }
    }

    private fun inflateFragment(fragment: Fragment? = null, addBackStack: Boolean = false) {
        val newFragment = fragment ?: (getNewFragment() ?: return)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.parent_view, newFragment, getTagFragment())
        if (addBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    private fun getNewFragment(): Fragment {
        return ContactBookFragment()
    }

    private fun getTagFragment(): String = "ContactBook"
}