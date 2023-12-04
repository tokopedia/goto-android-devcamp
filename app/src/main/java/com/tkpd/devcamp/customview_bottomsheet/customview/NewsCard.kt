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
        val attributeArray = context.obtainStyledAttributes(attributeSet, R.styleable.NewsCard)

        // get news_title value from xml
        val newsTitle = attributeArray.getString(R.styleable.NewsCard_news_title)

        // get news_author from xml
        val newsAuthor = attributeArray.getString(R.styleable.NewsCard_news_author)

        // get news_description from xml
        val newsDescription = attributeArray.getString(R.styleable.NewsCard_news_description)

        // get news_description from xml
        val newsImageUrl = attributeArray.getString(R.styleable.NewsCard_news_img_url)

        setupNewsCard(
            newsTitle = newsTitle,
            newsAuthor = newsAuthor,
            newsDescription = newsDescription,
            newsImageUrl = newsImageUrl
        )

        attributeArray.recycle()
    }

    private fun setupNewsCard(
        newsTitle: String? = null,
        newsAuthor: String? = null,
        newsDescription: CharSequence? = null,
        newsImageUrl: String? = null
    ) {
        setTitle(newsTitle ?: DEFAULT_TITLE)
        setAuthor(newsAuthor ?: DEFAULT_AUTHOR)
        setDescription(newsDescription ?: DEFAULT_DESCRIPTION)
        newsImageUrl?.let {
            setImageUrl(it)
        }

        setOnClick(
            newsTitle = newsTitle,
            newsSource = "Zac Johnson",
            newsAuthor = newsAuthor,
            newsUrl =  "https://biztoc.com/x/c191194496ce4396",
            newsImageUrl = newsImageUrl,
        )
    }

    fun setOnClick(
        newsTitle: String?,
        newsSource: String?,
        newsAuthor: String?,
        newsUrl: String?,
        newsImageUrl: String?,
    ) {
        binding.root.setOnClickListener {
            openBottomSheetDetail(
                News(
                    newsTitle,
                    newsSource,
                    newsAuthor,
                    newsUrl,
                    newsImageUrl,
                )
            )
        }
    }

    fun setImageUrl(url: String) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(binding.contentImg)
    }

    fun setTitle(title: String) {
        binding.contentTitle.text = title
    }

    fun setAuthor(author: String) {
        binding.contentAuthor.text = author
    }

    fun setDescription(description: CharSequence) {
        binding.contentDesc.text = description
    }

    private fun openBottomSheetDetail(news: News) {
        BottomSheetDetailNews.show((context as AppCompatActivity).supportFragmentManager, news)
    }

    companion object {
        private const val DEFAULT_TITLE = "Title"
        private const val DEFAULT_AUTHOR = "Author"
        private const val DEFAULT_DESCRIPTION =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
    }
}