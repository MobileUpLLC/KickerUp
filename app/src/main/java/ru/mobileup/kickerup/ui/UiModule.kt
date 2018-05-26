package ru.mobileup.kickerup.ui

import org.koin.android.module.AndroidModule
import org.koin.dsl.context.Context
import ru.mobileup.kickerup.ui.auth.AuthPm
import ru.mobileup.kickerup.ui.main.MainPm


class UiModule: AndroidModule() {

    override fun context(): Context  = applicationContext {
        provideFactory { MainPm() }
        provideFactory { AuthPm() }
    }
}