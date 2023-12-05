package com.tkpd.devcamp.practice.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp.practice.domain.repository.NewsRepository
import com.tkpd.devcamp.practice.presentation.uimodel.SearchNewsState
import kotlinx.coroutines.launch
import java.lang.Exception

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
        viewModelScope.launch {
            try {
                _keyword = keyword.ifEmpty { return@launch }

                _searchNewsState.value = SearchNewsState.Loading

                val result = newsRepository.searchNews(_keyword)

                _searchNewsState.value = SearchNewsState.Success(result)
            } catch (e: Exception) {
                _searchNewsState.value = SearchNewsState.Error(e)
            }
        }
    }
}