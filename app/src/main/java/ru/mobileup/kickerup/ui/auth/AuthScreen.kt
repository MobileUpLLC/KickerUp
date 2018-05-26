package ru.mobileup.kickerup.ui.auth

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.Screen


class AuthScreen: Screen<AuthPm>() {

    override val screenLayout: Int = R.layout.screen_auth

    override fun providePresentationModel(): AuthPm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.toolbar) {
            setTitle(R.string.screen_auth_title)
        }
    }

    override fun onBindPresentationModel(view: View, pm: AuthPm) {

    }
}