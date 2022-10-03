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
import com.tkpd.devcamp2022.day3.room_datastore.datastore.UserDataStoreManager
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalDataFragment: Fragment() {

    private var binding: FragmentLocalDataBinding? = null
    lateinit var dataStoreManager: UserDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            dataStoreManager = UserDataStoreManager(it)
        }
    }

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

        binding?.btnLogout?.setOnClickListener {
            clearDataStore()
        }

        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.getUserDataStore().catch { e ->
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    showLoginState()
                    hideLogoutState()
                }
            }.collect{
                withContext(Dispatchers.Main) {
                    if (it.isLoggedIn) {
                        showLogoutState()
                        hideLoginState()
                    } else {
                        showLoginState()
                        hideLogoutState()
                    }
                }
            }
        }

        viewModel.user.observe(viewLifecycleOwner) {
            hideProgressBar()
            isLoggedInSucceed(it)
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

    private fun saveToDataStore(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.saveToDataStore(user)
        }
    }

    private fun clearDataStore() {
        GlobalScope.launch(Dispatchers.IO) {
            dataStoreManager.clearUserDataStore()
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