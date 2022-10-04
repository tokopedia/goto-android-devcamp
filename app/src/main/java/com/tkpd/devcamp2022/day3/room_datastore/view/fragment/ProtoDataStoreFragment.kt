package com.tkpd.devcamp2022.day3.room_datastore.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.databinding.FragmentDataStoreBinding
import com.tkpd.devcamp2022.day3.room_datastore.api.MockUserApi
import com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel.UserViewModel

class ProtoDataStoreFragment: Fragment() {

    private var binding: FragmentDataStoreBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataStoreBinding.inflate(layoutInflater, container, false)
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

    }
}