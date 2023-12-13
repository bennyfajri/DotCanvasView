package com.beni.core.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.beni.core.data.local.room.PointEntitiy

@SuppressLint("ViewConstructor")
class CanvasView(
    context: Context,
    private val listPoint: List<PointEntitiy>
) : View(context) {
    private val dotPaint = Paint()

    init {
        dotPaint.color = Color.RED
        dotPaint.style = Paint.Style.FILL
        dotPaint.strokeWidth = 12f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        listPoint.forEach { point ->
            canvas.drawCircle(point.x, point.y, dotPaint.strokeWidth, dotPaint)
        }
    }
}