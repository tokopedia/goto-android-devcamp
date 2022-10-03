package com.tkpd.devcamp2022.day3.room_datastore.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.FragmentLocalDataBinding
import com.tkpd.devcamp2022.day3.room_datastore.api.MockUserApi
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel.UserViewModel

class LocalDataFragment: Fragment() {

    private var binding: FragmentLocalDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocalDataBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private val viewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                ): T {
                    return UserViewModel(MockUserApi()) as T
                }
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogin?.setOnClickListener {
            showProgressBar()
            viewModel.getUserLogin(
                binding?.etUsername?.text.toString(),
                binding?.etPassword?.text.toString()
            )
        }

        viewModel.user.observe(viewLifecycleOwner) {
            hideProgressBar()
            isLoggedInSucceed(it)
        }
    }


    private fun isLoggedInSucceed(user: User) {
        if (user.isLoggedIn) {
            showToaster(getString(R.string.datastore_login_succeed))
        } else {
            showToaster(getString(R.string.datastore_login_failed))
        }
    }

    private fun showToaster(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding?.progressBar?.visibility = View.GONE
    }
}