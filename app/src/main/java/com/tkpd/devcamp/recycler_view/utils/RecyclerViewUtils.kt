package com.tkpd.devcamp.recycler_view.utils

import android.widget.LinearLayout

val customRecyclerViewDimension = LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.MATCH_PARENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
).apply {
    setMargins(16, 16, 16, 16)
}