package ru.mobileup.kickerup.ui.common.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

/**
 * Base [AbsListItemAdapterDelegate] subclass for easy use.
 */
abstract class BaseAdapterDelegate<I : T, T, VH : BaseAdapterDelegate.ViewHolder<I>> : AbsListItemAdapterDelegate<I, T, VH>() {

    abstract fun isForViewType(item: T): Boolean

    override fun isForViewType(item: T, items: MutableList<T>, position: Int): Boolean {
        return isForViewType(item)
    }

    override fun onBindViewHolder(item: I, viewHolder: VH, payloads: MutableList<Any>) {
        viewHolder.bind(item)
    }

    /**
     * Bindable [RecyclerView.ViewHolder].
     */
    abstract class ViewHolder<in I>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: I)
    }
}