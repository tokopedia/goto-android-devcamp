package com.tkpd.devcamp.viewmodel_livedata.state

import com.tkpd.devcamp.recycler_view.model.Article

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
sealed class ArticleScreenState {
    object Loading: ArticleScreenState()
    data class Success(val list: List<Article>) : ArticleScreenState()
    object Error: ArticleScreenState()
}