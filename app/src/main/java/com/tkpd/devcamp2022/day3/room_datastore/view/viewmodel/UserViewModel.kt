package com.tkpd.devcamp2022.day3.room_datastore.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.room_datastore.api.user.UserApi
import com.tkpd.devcamp2022.day3.room_datastore.model.User
import kotlinx.coroutines.launch

class UserViewModel(
    private val api: UserApi
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUserLogin(userName: String, password: String) {
        viewModelScope.launch {
            _user.value = api.loginApi(userName, password)
        }
    }
}