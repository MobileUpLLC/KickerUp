package ru.mobileup.kickerup.domain

import org.koin.android.module.AndroidModule
import org.koin.dsl.context.Context


class InteractorModule : AndroidModule() {

    override fun context(): Context = applicationContext {
        provideFactory { GetLeaderboardInteractor() }
    }
}