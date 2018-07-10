package ru.mobileup.kickerup.ui.main

import android.content.Context
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View


class BottomBarBehavior : CoordinatorLayout.Behavior<View> {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is BottomAppBar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        val bottomPadding = child.bottom - dependency.top

        if (bottomPadding > 0) {
            child.setPaddingRelative(0, 0, 0, bottomPadding)
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }
}