package com.tkpd.devcamp.customview_bottomsheet.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class StarButton : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private var isActive = false

    init {
        minimumWidth = 100
        minimumHeight = 100

        setOnClickListener {
            isActive = !isActive
            invalidate()
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.let {
            val paint = Paint().apply {
                color = if (!isActive) {
                    Color.GRAY
                } else {
                    Color.GREEN
                }
            }

            var mid = (width / 2).toFloat()
            val min = width.coerceAtMost(height).toFloat()
            val half = min / 2
            mid -= half
            val newPath = Path()

            // top left
            newPath.moveTo(mid + half * 0.5f, half * 0.84f);
            // top right
            newPath.lineTo(mid + half * 1.5f, half * 0.84f);
            // bottom left
            newPath.lineTo(mid + half * 0.68f, half * 1.45f);
            // top tip
            newPath.lineTo(mid + half * 1.0f, half * 0.5f);
            // bottom right
            newPath.lineTo(mid + half * 1.32f, half * 1.45f);
            // top left
            newPath.lineTo(mid + half * 0.5f, half * 0.84f);

            canvas.drawPath(newPath, paint)

            paint.strokeWidth = 3f
            paint.style = Paint.Style.STROKE

            canvas.drawRect(1f, 1f, canvas.width.toFloat() - 1f, canvas.height.toFloat() - 1f, paint)
        }

        super.draw(canvas)
    }
}