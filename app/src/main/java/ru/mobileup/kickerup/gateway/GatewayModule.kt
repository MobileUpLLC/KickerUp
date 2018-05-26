package ru.mobileup.kickerup.gateway

import org.koin.android.module.AndroidModule
import org.koin.dsl.context.Context


class GatewayModule : AndroidModule() {

    override fun context(): Context = applicationContext {
        // TODO: provide gateways
    }
}