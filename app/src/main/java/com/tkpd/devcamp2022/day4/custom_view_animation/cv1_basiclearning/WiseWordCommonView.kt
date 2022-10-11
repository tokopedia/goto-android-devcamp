package com.tkpd.devcamp2022.day4.custom_view_animation.cv1_basiclearning

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.tkpd.devcamp2022.R

class WiseWordCommonView: CardView {

    constructor(context: Context): super(context)

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(
        context,
        attrs
    ) {
        obtainAttributes(
            context = context,
            attrs = attrs
        )
    }

    private var tvDescription: TextView
    private var tvName: TextView

    init {
        inflate(context, R.layout.layout_wise_word_view_layout, this)
        tvDescription = findViewById(R.id.tv_description)
        tvName = findViewById(R.id.tv_name)
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
            R.styleable.WiseWordCommonView
        ).apply {
            try {
                tvDescription.text = getString(R.styleable.WiseWordCommonView_textDescription)
                tvName.text = getString(R.styleable.WiseWordCommonView_textName)
            } finally {
                recycle()
            }
        }
    }

}