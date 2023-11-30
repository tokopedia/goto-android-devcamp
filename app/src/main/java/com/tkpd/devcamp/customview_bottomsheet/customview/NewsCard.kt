package com.tkpd.devcamp.customview_bottomsheet.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.tkpd.devcamp.R
import com.tkpd.devcamp.customview_bottomsheet.BottomSheetDetailNews
import com.tkpd.devcamp.customview_bottomsheet.News
import com.tkpd.devcamp.databinding.NewsCardLayoutBinding

class NewsCard : ConstraintLayout {
    private val binding: NewsCardLayoutBinding =
        NewsCardLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    // primary constructor
    constructor(context: Context) : super(context) {
        setupNewsCard()
    }

    // constructor for XML implementation
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initializeViewWithAttribute(attributeSet)
    }

    // constructor for initialize and set data
    constructor(
        context: Context,
        newsTitle: String,
        newsAuthor: String,
        newsDescription: CharSequence,
        newsImageUrl: String? = null
    ) : super(context) {
        setupNewsCard(newsTitle, newsAuthor, newsDescription, newsImageUrl)
    }

    private fun initializeViewWithAttribute(attributeSet: AttributeSet) {
        // TODO: Get all attribute value related to NewsCard and bind it to UI
    }

    private fun setupNewsCard(
        newsTitle: String? = null,
        newsAuthor: String? = null,
        newsDescription: CharSequence? = null,
        newsImageUrl: String? = null
    ) {
        // TODO #1: Set provided data to related UI

        // TODO #2: Add listener for bottom sheet
        // setOnClick(...)
    }

    private fun setOnClick(newsTitle: String?, newsAuthor: String?, newsImageUrl: String?) {
        // TODO: Bind click listener to news card & trigger bottom sheet on click
    }

    fun setImageUrl(url: String) {
        // TODO: Load image url using Glide
    }

    fun setTitle(title: String) {
        // TODO: Set param title to title textview
    }

    fun setAuthor(author: String) {
        // TODO: Set param author to author textview
    }

    fun setDescription(description: CharSequence) {
        // TODO: Set param description to description textview
    }

    private fun openBottomSheetDetail(news: News) {
        // TODO: Trigger bottom sheet
    }

    companion object {
        private const val DEFAULT_TITLE = "Title"
        private const val DEFAULT_AUTHOR = "Author"
        private const val DEFAULT_DESCRIPTION =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
    }
}