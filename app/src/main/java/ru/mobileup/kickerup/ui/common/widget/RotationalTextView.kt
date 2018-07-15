package ru.mobileup.kickerup.ui.common.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author Kulikov Konstantin
 * @since 15.07.2018.
 */
class RotationalTextView : TextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}