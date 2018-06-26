package ru.mobileup.kickerup.ui.auth

import ru.mobileup.kickerup.ui.ShowLeaderboardScreen
import ru.mobileup.kickerup.ui.common.ScreenPm


class AuthPm: ScreenPm() {

    val authClicks = Action<Unit>()

    override fun onCreate() {
        super.onCreate()

        authClicks.observable
            .subscribe { sendNavigationMessage(ShowLeaderboardScreen()) }
            .untilDestroy()
    }
}