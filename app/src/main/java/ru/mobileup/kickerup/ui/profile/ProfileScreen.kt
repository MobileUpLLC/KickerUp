package ru.mobileup.kickerup.ui.profile

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class ProfileScreen: Screen<ProfilePm>(), RhombusController, BottomBarController {

    override val screenLayout: Int = R.layout.screen_profile

    override val rhombusX: Float = -630f

    override val rhombusY: Float = -550f

    override fun providePresentationModel(): ProfilePm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.toolbar) {
            setTitle(R.string.profile)
        }
    }

    override fun onBindPresentationModel(view: View, pm: ProfilePm) {

    }
}