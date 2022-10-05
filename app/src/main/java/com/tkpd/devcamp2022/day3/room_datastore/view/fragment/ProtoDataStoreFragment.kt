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
    lateinit var dataStoreManager: UserProtoDataStoreManager

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
            dataStoreManager = UserProtoDataStoreManager(it)
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
            clearDataStore()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            hideProgressBar()
            isLoggedInSucceed(it)
        }

        getDataStore()
    }

    private fun saveToDataStore(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.saveToProtoDataStore(user)
        }
    }

    private fun clearDataStore() {
        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.clearUserProtoDataStore()
        }
    }

    private fun getDataStore() {
        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.getUserProtoDataStore().catch { e ->
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    userNotLoggedIn()
                }
            }.collect{
                withContext(Dispatchers.Main) {
                    if (it.isLoggedIn) {
                        userAlreadyLogin()
                    } else {
                        userNotLoggedIn()
                    }
                }
            }
        }
    }

    private fun isLoggedInSucceed(user: User) {
        if (user.isLoggedIn) {
            showToaster(getString(R.string.datastore_login_succeed))
            saveToDataStore(user)
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
        binding?.btnLogout?.visibility = View.VISIBLE
    }

    private fun hideLogoutState() {
        binding?.btnLogout?.visibility = View.GONE
    }
}