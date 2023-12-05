package com.tkpd.devcamp.practice.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp.practice.domain.repository.NewsRepository
import com.tkpd.devcamp.practice.presentation.uimodel.SearchNewsState
import kotlinx.coroutines.launch

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class SearchNewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var _keyword = ""

    private val _searchNewsState = MutableLiveData<SearchNewsState>()
    val searchNewsState: LiveData<SearchNewsState> = _searchNewsState

    fun searchNews(keyword: String = _keyword) {
        _keyword = keyword.ifEmpty { return }

        viewModelScope.launch {
            TODO("Change state to 'Loading' before starting any logic")
            TODO("Get data from repository & change the state to 'Success'")
            TODO("If there is an exception during the process, catch it and change state to 'Error'")
        }
    }
}