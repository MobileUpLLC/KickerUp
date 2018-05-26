package ru.mobileup.kickerup.system

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.support.annotation.*

/**
 * Helps to get resources without importing the context.
 */
class ResourceHelper(private val context: Context) {

    fun getString(@StringRes stringRes: Int): String =
            context.getString(stringRes)

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String =
            context.getString(stringRes, *formatArgs)

    fun getStringsList(@ArrayRes arrayRes: Int) =
            context.resources.getStringArray(arrayRes).toList()

    fun getDimen(@DimenRes dimenRes: Int): Float = context.resources.getDimension(dimenRes)

    fun getDrawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null) =
        context.resources.getDrawable(drawableRes, theme)

    fun getColor(@ColorRes colorRes: Int, theme: Resources.Theme? = null) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(colorRes, theme)
        } else {
            context.resources.getColor(colorRes)
        }
}