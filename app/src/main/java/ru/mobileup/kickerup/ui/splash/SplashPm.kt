package ru.mobileup.kickerup.ui.splash

import ru.mobileup.kickerup.ui.ShowAuthScreen
import ru.mobileup.kickerup.ui.common.ScreenPm


class SplashPm: ScreenPm() {

    val startTextAnimation = Command<Unit>()
    val animationCanceled = Action<Unit>()

    override fun onBind() {
        super.onBind()

        startTextAnimation.consumer.accept(Unit)
    }

    override fun onCreate() {
        super.onCreate()

        animationCanceled.observable
            .subscribe { sendNavigationMessage(ShowAuthScreen()) }
            .untilDestroy()
    }
}