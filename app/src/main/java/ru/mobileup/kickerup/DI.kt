package ru.mobileup.kickerup

import ru.mobileup.kickerup.domain.InteractorModule
import ru.mobileup.kickerup.gateway.GatewayModule
import ru.mobileup.kickerup.system.SystemModule
import ru.mobileup.kickerup.ui.UiModule

/**
 * All DI modules as a list.
 */
fun allModules() = listOf(
        AppModule(),
        SystemModule(),
        GatewayModule(),
        InteractorModule(),
        UiModule()
)

/**
 * DI scopes names.
 */
object Scopes {
    // None for now
}
