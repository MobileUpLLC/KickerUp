package ru.mobileup.kickerup.ui.common

import android.support.design.bottomappbar.BottomAppBar


interface BottomBarController {

    val fabAlignmentMode: Int
        get() = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

    fun onFubClickListener() = {
        // do nothing
    }
}