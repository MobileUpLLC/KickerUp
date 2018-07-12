package ru.mobileup.kickerup.ui.common

import android.support.design.bottomappbar.BottomAppBar


interface BottomBarController {

    val fabAlignmentMode: Int
        get() = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

    val showTabs: Boolean
        get() = false

    fun onFubClickListener() {
        // do nothing
    }

    val showBackButton: Boolean
        get() = false
}