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
            // TODO: Show bottom sheet
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomsheetDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // TODO: Initiate dialog on dialog show
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetailNews()
    }

    private fun setDetailNews() {
        // TODO: Bind data to related UI
    }

    private fun openWebPage(url: String?) {
        // TODO: Open webview
    }
}