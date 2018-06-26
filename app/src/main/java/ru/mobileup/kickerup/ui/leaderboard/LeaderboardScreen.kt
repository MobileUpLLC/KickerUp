package ru.mobileup.kickerup.ui.leaderboard

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class LeaderboardScreen: Screen<LeaderboardPm>(), RhombusController, BottomBarController {

    override val screenLayout: Int = R.layout.screen_leaderboard

    override val rhombusX: Float = 530f

    override val rhombusY: Float = -600f

    override fun providePresentationModel(): LeaderboardPm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.toolbar) {
            setTitle(R.string.leaderboard)
        }
    }

    override fun onBindPresentationModel(view: View, pm: LeaderboardPm) {

    }
}