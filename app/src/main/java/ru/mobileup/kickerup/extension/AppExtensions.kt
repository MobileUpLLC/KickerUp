package ru.mobileup.kickerup.extension

import android.content.res.Resources
import android.os.Build
import org.koin.KoinContext
import org.koin.dsl.context.Context
import java.util.*

fun Iterable<Boolean>.isAllTrue() = all { it }

inline fun <reified T> Context.getPropertyOrNull(key: String): T? =
    koinContext.propertyResolver.properties[key] as? T

fun KoinContext.setOrRemoveProperty(key: String, property: Any?) {
    if (property == null) {
        removeProperties(key)
    } else {
        setProperty(key, property)
    }
}

fun Resources.dp(px: Int) = this.displayMetrics.density * px