package com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tkpd.devcamp2022.day3.room_datastore.domain.UserListState
import com.tkpd.devcamp2022.day3.room_datastore.repository.UserListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userListRepository: UserListRepository
): ViewModel() {

    private val _stateUserList = MutableLiveData<UserListState>()
    val stateUserList: LiveData<UserListState>
        get() = _stateUserList

    fun getUserList() {
        CoroutineScope(Dispatchers.IO).launch {
            val state = userListRepository.getUserList()
            _stateUserList.postValue(state)
        }
    }
}