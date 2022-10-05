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
import androidx.room.Room
import com.tkpd.devcamp2022.MainApplication
import com.tkpd.devcamp2022.databinding.FragmentRoomBinding
import com.tkpd.devcamp2022.day3.room_datastore.api.userlist.UserListApiClient
import com.tkpd.devcamp2022.day3.room_datastore.db.dao.AppDatabase
import com.tkpd.devcamp2022.day3.room_datastore.repository.state.UserListState
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
                    return UserListViewModel(
                        UserListRepositoryImpl(
                            UserListApiClient.create(),
                            (activity?.application as MainApplication).database.userListDao()
                        )
                    ) as T
                }
            }
        }
    )

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
        binding?.roomSwipeRefresh?.setOnRefreshListener {
            fetchUserList()
        }
    }

    private fun fetchUserList() {
        loadingState()
        binding?.toggleRoom?.isChecked?.let {
            viewModel.getUserList(it)
        }
    }

    private fun observeUserList() {
        viewModel.stateUserList.observe(viewLifecycleOwner){
            binding?.roomSwipeRefresh?.isRefreshing = false
            when(it) {
                is UserListState.SuccessGetUserList -> onSuccessGetUserList(it.userListResponse)
                is UserListState.ErrorGetUserList -> onErrorGetUserList(it.e)
            }
        }
    }

    private fun onSuccessGetUserList(userList: UsersList?) {
        if(userList != null) {
            showUserList(userList.data)
        } else {
            showEmptyState()
        }
    }

    private fun onErrorGetUserList(e: Exception) {
        showEmptyState(e.message.toString())
    }

    private fun showUserList(users: List<UserData>) {
        binding?.tvEmptyState?.visibility = View.GONE
        binding?.rvUsers?.visibility = View.VISIBLE
        binding?.rvUsers?.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            userAdapter.setUsers(users)
        }
    }

    private fun showEmptyState(title: String = "") {
        binding?.tvEmptyState?.visibility = View.VISIBLE
        binding?.rvUsers?.visibility = View.GONE
        if (title.isNotEmpty()) {
            binding?.tvEmptyState?.text = title
        }
    }

    private fun loadingState() {
        binding?.tvEmptyState?.visibility = View.GONE
        binding?.rvUsers?.visibility = View.GONE
    }
}