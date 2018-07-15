package ru.mobileup.kickerup.ui.startgame

import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.view.View
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class StartGameScreen : Screen<StartGamePm>(), RhombusController, BottomBarController {

    override val rhombusX: Float = 0f

    override val rhombusY: Float = 0f

    override val screenLayout: Int = R.layout.screen_start_game

    override val showBackButton: Boolean = true

    override val fabAlignmentMode: Int = BottomAppBar.FAB_ALIGNMENT_MODE_END

    override fun providePresentationModel(): StartGamePm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.toolbar) {
            setTitle(R.string.screen_start_game_title)
        }
    }

    override fun onBindPresentationModel(view: View, pm: StartGamePm) {

    }
}