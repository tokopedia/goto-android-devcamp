package com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.TitleUiModel

class TitleViewHolder(itemView: View) : HomeViewHolder(itemView) {

    private val tvLabel = itemView.findViewById<TextView>(R.id.tv_title)

    fun bind(titleUiModel: TitleUiModel) {
        tvLabel.text = titleUiModel.label
    }
}