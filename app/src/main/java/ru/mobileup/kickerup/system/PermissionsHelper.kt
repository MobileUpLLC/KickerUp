package ru.mobileup.kickerup.system

import android.app.Activity
import android.content.ActivityNotFoundException
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class PermissionsHelper {

    private var rxPermissions: RxPermissions? = null
    private var activity: Activity? = null

    fun setActivity(activity: Activity?) {
        this.activity = activity
        rxPermissions = activity?.let { RxPermissions(it) }
    }

    fun request(vararg permissions: String): Observable<Boolean> =
        rxPermissions?.request(*permissions)
                ?: throw ActivityNotFoundException("Set activity for PermissionsHelper before usage")

    fun <T> ensure(vararg permissions: String): ObservableTransformer<T, Boolean> =
        rxPermissions?.ensure(*permissions)
                ?: throw ActivityNotFoundException("Set activity for PermissionsHelper before usage")
}