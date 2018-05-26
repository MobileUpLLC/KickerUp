package ru.mobileup.kickerup.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.dmdev.rxpm.base.PmController


abstract class Screen<PM : ScreenPm>(bundle: Bundle? = null) : PmController<PM>(bundle) {

    protected val context: Context? get() = activity

    protected open val fitWindowBottomOffset = false

    protected abstract val screenLayout: Int

    override fun createView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        return inflater.inflate(screenLayout, container, false).also {
            onInitView(it, savedViewState)
        }
    }

    /**
     * Use for views initialisation.
     */
    protected open fun onInitView(view: View, savedViewState: Bundle?) {

        if (fitWindowBottomOffset) {
            view.setOnApplyWindowInsetsListener { v, insets ->
                view.setPadding(0, 0, 0, insets.systemWindowInsetBottom)
                insets
            }
        }
    }

    final override fun onBindPresentationModel(pm: PM) {
        onBindPresentationModel(view!!, pm)
    }

    abstract fun onBindPresentationModel(view: View, pm: PM)

    override fun handleBack(): Boolean {
        passTo(presentationModel.backAction.consumer)
        return true
    }
}