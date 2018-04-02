package com.huangyu.readhub.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.text.style.ReplacementSpan
import android.util.TypedValue

/**
 * Created by huangyu on 2018/3/28.
 */
class CustomVerticalCenterSpan(val context: Context?, private val fontSizeSp: Float) : ReplacementSpan() {

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt): Int {
        var txt = text
        txt = txt.subSequence(start, end)
        val p = getCustomTextPaint(paint)
        return p.measureText(txt.toString()).toInt()
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        var txt = text
        txt = txt.subSequence(start, end)
        val p = getCustomTextPaint(paint)
        val fm = p.fontMetricsInt
        // 此处重新计算y坐标，使字体居中
        canvas.drawText(txt.toString(), x, (y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2)).toFloat(), p)
    }

    private fun getCustomTextPaint(srcPaint: Paint): TextPaint {
        val paint = TextPaint(srcPaint)
        // 设定字体大小, sp转换为px
        paint.textSize = spToPx(fontSizeSp)
        return paint
    }

    fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context?.resources?.displayMetrics)
    }

}