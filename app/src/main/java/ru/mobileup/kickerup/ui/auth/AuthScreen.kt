package ru.mobileup.kickerup.ui.auth

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.screen_auth.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class AuthScreen: Screen<AuthPm>(), RhombusController {

    override val screenLayout: Int = R.layout.screen_auth

    override val rhombusX: Float = 450f

    override val rhombusY: Float = -550f

    override fun providePresentationModel(): AuthPm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.toolbar) {
            setTitle(R.string.screen_auth_title)
        }
    }

    override fun onBindPresentationModel(view: View, pm: AuthPm) {
        view.passwordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                passTo(pm.authClicks.consumer)
                true
            } else {
                false
            }
        }
    }
}