package ru.mobileup.kickerup.system

import org.koin.android.module.AndroidModule
import ru.mobileup.kickerup.system.OutIntentsHelper
import ru.mobileup.kickerup.system.ResourceHelper

class SystemModule: AndroidModule() {

    override fun context() = applicationContext {
        provide { ResourceHelper(context) }
        provide { OutIntentsHelper(context) }
        provide { PermissionsHelper() }
    }
}