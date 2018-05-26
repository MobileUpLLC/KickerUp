package ru.mobileup.kickerup.ui.common.list

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

/**
 * Base [ListDelegationAdapter] implementation for easy use.
 *
 * Note: [setItems] method calls [notifyDataSetChanged].
 */
open class BaseListDelegationAdapter<T>(vararg delegates: AdapterDelegate<List<T>>) : ListDelegationAdapter<List<T>>() {

    init {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }

    override fun setItems(items: List<T>?) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}