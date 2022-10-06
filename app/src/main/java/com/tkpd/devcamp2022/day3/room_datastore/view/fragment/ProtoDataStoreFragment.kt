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
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.FragmentDataStoreBinding
import com.tkpd.devcamp2022.day3.room_datastore.api.user.MockUserApi
import com.tkpd.devcamp2022.day3.room_datastore.datastore.UserProtoDataStoreManager
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProtoDataStoreFragment: Fragment() {

    private var binding: FragmentDataStoreBinding? = null
    //TODO(3,7) - Initialize Proto DataStore


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            //TODO(3,7) - Initialize Proto DataStore
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataStoreBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogin?.setOnClickListener {
            showProgressBar()
            viewModel.getUserLogin(
                binding?.etUsername?.text.toString(),
                binding?.etPassword?.text.toString()
            )
        }

        binding?.btnLogout?.setOnClickListener {
            //TODO(3,9) - Access Delete from Proto DataStore
        }

        viewModel.user.observe(viewLifecycleOwner) {
            hideProgressBar()
            isLoggedInSucceed(it)
        }

        //TODO(3,10) - Access Fetch from Proto DataStore
    }

    //TODO(3,8) - Access Save to Proto DataStore


    //TODO(3,9) - Access Delete from Proto DataStore

    //TODO(3,10) - Access Fetch from Proto DataStore


    private fun isLoggedInSucceed(user: User) {
        if (user.isLoggedIn) {
            showToaster(getString(R.string.datastore_login_succeed))
            //TODO(3,8) - Access Save to Proto DataStore
            hideLoginState()
            showLogoutState()
        } else {
            showToaster(getString(R.string.datastore_login_failed))
        }
    }

    private fun userAlreadyLogin() {
        showLogoutState()
        hideLoginState()
    }

    private fun userNotLoggedIn() {
        showLoginState()
        hideLogoutState()
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

    private fun hideLoginState() {
        binding?.etPassword?.visibility = View.GONE
        binding?.etUsername?.visibility = View.GONE
        binding?.btnLogin?.visibility = View.GONE
    }

    private fun showLoginState() {
        binding?.etPassword?.visibility = View.VISIBLE
        binding?.etUsername?.visibility = View.VISIBLE
        binding?.btnLogin?.visibility = View.VISIBLE
    }

    private fun showLogoutState() {
        binding?.tvUser?.visibility = View.VISIBLE
        binding?.btnLogout?.visibility = View.VISIBLE
    }

    private fun hideLogoutState() {
        binding?.btnLogout?.visibility = View.GONE
        binding?.tvUser?.visibility = View.GONE
    }

    private fun setUserName(userName: String) {
        binding?.tvUser?.text = userName
    }
}