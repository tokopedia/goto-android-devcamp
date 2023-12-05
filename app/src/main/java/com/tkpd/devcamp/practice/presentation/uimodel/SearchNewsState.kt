package com.tkpd.devcamp.practice.presentation.uimodel

import com.tkpd.devcamp.practice.domain.model.NewsArticle
import java.lang.Exception

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
sealed interface SearchNewsState {

    object Loading : SearchNewsState

    data class Success(val list: List<NewsArticle>) : SearchNewsState

    data class Error(val exception: Exception) : SearchNewsState
}
