package com.tkpd.devcamp.customview_bottomsheet

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tkpd.devcamp.R
import com.tkpd.devcamp.databinding.BottomsheetDetailNewsBinding

class BottomSheetDetailNews(private val news: News) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetDetailNewsBinding

    companion object {
        private const val TAG_BOTTOM_SHEET_DETAIL_NEWS = "bottom_sheet_detail_news"
        fun show(fragmentManager: FragmentManager, news: News) {
            BottomSheetDetailNews(news).show(fragmentManager, TAG_BOTTOM_SHEET_DETAIL_NEWS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomsheetDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetailNews()
    }

    private fun setDetailNews() {
        binding.apply {
            tvTitle.text = news.title
            tvSourceName.text = news.sourceName
            tvAuthor.text = news.author
            Glide.with(this@BottomSheetDetailNews)
                .load(news.urlImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                .into(ivNews)

            btnToNews.setOnClickListener {
                dismiss()
                openWebPage(news.url)
            }
        }
    }

    private fun openWebPage(url: String?) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }
}