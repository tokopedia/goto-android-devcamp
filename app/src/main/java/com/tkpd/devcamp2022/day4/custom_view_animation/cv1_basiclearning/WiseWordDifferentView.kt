package com.tkpd.devcamp2022.day4.custom_view_animation.cv1_basiclearning

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.LayoutWiseWordViewLayoutBinding

class WiseWordDifferentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private var binding: LayoutWiseWordViewLayoutBinding

    var tvDescription: TextView
    var tvName: TextView

    init {
        binding = LayoutWiseWordViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        tvDescription = binding.tvDescription
        tvName = binding.tvName

        if (attrs != null) {
            obtainAttributes(context, attrs)
        }
    }

    var textDescription: String
        get() = tvDescription.text.toString()
        set(value) {
            tvDescription.text = value
        }

    var textName: String
        get() = tvName.text.toString()
        set(value) {
            tvName.text = value
        }

    private fun obtainAttributes(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.WiseWordDifferentView
        ).apply {
            try {
                tvDescription.text = getString(R.styleable.WiseWordDifferentView_textDescription)
                tvName.text = getString(R.styleable.WiseWordDifferentView_textName)
            } finally {
                recycle()
            }
        }
    }
}