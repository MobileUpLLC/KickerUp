package ru.mobileup.kickerup.ui

import org.koin.android.module.AndroidModule
import org.koin.dsl.context.Context
import ru.mobileup.kickerup.ui.auth.AuthPm
import ru.mobileup.kickerup.ui.leaderboard.LeaderboardPm
import ru.mobileup.kickerup.ui.main.MainPm
import ru.mobileup.kickerup.ui.splash.SplashPm


class UiModule: AndroidModule() {

    override fun context(): Context  = applicationContext {
        provideFactory { MainPm() }
        provideFactory { AuthPm() }
        provideFactory { SplashPm() }
        provideFactory { LeaderboardPm() }
    }
}