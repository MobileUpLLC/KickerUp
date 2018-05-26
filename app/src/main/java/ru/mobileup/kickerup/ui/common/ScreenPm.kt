package ru.mobileup.kickerup.ui.common

import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.navigation.NavigationMessage
import ru.mobileup.kickerup.ui.Back


abstract class ScreenPm : PresentationModel() {

    private val defaultBackAction = Action<Unit>()

    open val backAction = defaultBackAction

    override fun onCreate() {
        super.onCreate()

        defaultBackAction.observable
            .subscribe { sendNavigationMessage(Back()) }
            .untilDestroy()
    }

    protected fun sendNavigationMessage(message: NavigationMessage) {
        navigationMessages.consumer.accept(message)
    }
}