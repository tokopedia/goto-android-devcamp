package com.tkpd.devcamp2022.day3.room_datastore.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.devcamp2022.databinding.FragmentRoomBinding
import com.tkpd.devcamp2022.day3.room_datastore.api.userlist.UserListApiClient
import com.tkpd.devcamp2022.day3.room_datastore.domain.UserListState
import com.tkpd.devcamp2022.day3.room_datastore.model.UserData
import com.tkpd.devcamp2022.day3.room_datastore.model.UsersList
import com.tkpd.devcamp2022.day3.room_datastore.repository.UserListRepositoryImpl
import com.tkpd.devcamp2022.day3.room_datastore.view.adapter.UserAdapter
import com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel.UserListViewModel

class RoomFragment: Fragment() {

    private var binding: FragmentRoomBinding? = null
    private val userAdapter: UserAdapter = UserAdapter()

    private val viewModel by viewModels<UserListViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                ): T {
                    return UserListViewModel(UserListRepositoryImpl(UserListApiClient.create())) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUserList()
        observeUserList()
    }

    private fun fetchUserList() {
        viewModel.getUserList()
    }

    private fun observeUserList() {
        viewModel.stateUserList.observe(viewLifecycleOwner){
            when(it) {
                is UserListState.SuccessGetUserList -> onSuccessGetUserList(it.userListResponse)
                is UserListState.ErrorGetUserList -> onErrorGetUserList(it.e)
            }
        }
    }

    private fun onSuccessGetUserList(userList: UsersList) {
        showUserList(userList.data)
    }

    private fun onErrorGetUserList(e: Exception) {

    }

    private fun showUserList(users: List<UserData>) {
        binding?.rvUsers?.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            userAdapter.setUsers(users)
        }
    }
}