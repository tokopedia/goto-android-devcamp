package com.tkpd.devcamp.practice.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp.practice.di.DependencyProvider
import com.tkpd.devcamp.practice.presentation.viewmodel.SearchNewsViewModel

/**
 * Created By : Jonathan Darwin on December 04, 2023
 */
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchNewsViewModel::class.java)) {
            SearchNewsViewModel(
                newsRepository = DependencyProvider.provideNewsRepository()
            ) as T
        } else {
            throw IllegalArgumentException("Unknown viewmodel")
        }
    }
}