package com.tkpd.devcamp.connect_to_internet.domain

import com.tkpd.devcamp.connect_to_internet.data.FourthRepository
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType.AUTH
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType.CLIENT
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType.NETWORK
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType.SERVER
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType.UNKNOWN
import com.tkpd.devcamp.connect_to_internet.network.ApiResult.Error
import com.tkpd.devcamp.connect_to_internet.network.ApiResult.Success
import com.tkpd.devcamp.connect_to_internet.presentation.FourthView
import com.tkpd.devcamp.recycler_view.model.Article
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface FourthPresenter {
    fun getTopHeadlines()
}

class FourthPresenterImpl(
    private val view: FourthView,
    private val fourthRepository: FourthRepository,
    private val uiScope: CoroutineContext = Dispatchers.Main,
    override val coroutineContext: CoroutineContext = uiScope + Job()
) : FourthPresenter, CoroutineScope {
    override fun getTopHeadlines() {
        launch {
            view.showLoading()
            //TODO section-4 calling api
            //TODO section-4 handling error
            //TODO section-4 handling success
        }
    }
}