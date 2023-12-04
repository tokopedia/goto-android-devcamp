package com.tkpd.devcamp.viewmodel_livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tkpd.devcamp.connect_to_internet.data.FourthRepository
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType
import com.tkpd.devcamp.connect_to_internet.network.ApiResult
import com.tkpd.devcamp.recycler_view.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FifthViewModel(
    private val uiScope: CoroutineContext = Dispatchers.Main,
    override val coroutineContext: CoroutineContext = uiScope + Job()
) : ViewModel(), CoroutineScope {

    private lateinit var repository: FourthRepository

    fun setupRepository(repository: FourthRepository) {
        this.repository = repository
    }

    private val _state = MutableLiveData<ArticleScreenState>()
    val state: LiveData<ArticleScreenState> = _state

    fun getTopHeadline() {
        launch {
            _state.postValue(ArticleScreenState.Loading)
            when (val result = repository.getTopHeadlines()) {
                is ApiResult.Error -> {
                    when (result.apiErrorType) {
                        ApiErrorType.AUTH -> {}
                        is ApiErrorType.CLIENT -> {
                            result.apiErrorType.errors
                        }

                        ApiErrorType.NETWORK -> {}
                        ApiErrorType.SERVER -> {}
                        ApiErrorType.UNKNOWN -> {}
                    }
                    _state.postValue(ArticleScreenState.Error)
                }

                is ApiResult.Success -> {
                    val articles = result.data.articles.map { Article(it) }
                    _state.postValue(ArticleScreenState.Success(articles))
                }
            }
        }
    }
}

sealed class ArticleScreenState {
    object Loading: ArticleScreenState()
    data class Success(val list: List<Article>) : ArticleScreenState()
    object Error: ArticleScreenState()
}