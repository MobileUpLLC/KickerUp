package ru.mobileup.kickerup.ui.leaderboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.screen_leaderboard.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class LeaderboardScreen: Screen<LeaderboardPm>(), RhombusController, BottomBarController {

    override val screenLayout: Int = R.layout.screen_leaderboard

    override val rhombusX: Float = 630f

    override val rhombusY: Float = -550f

    private val leaderboardAdapter = LeaderboardAdapter(presentationModel.userSelected.consumer::accept)

    override fun providePresentationModel(): LeaderboardPm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = leaderboardAdapter
        }

        with(view.toolbar) {
            setTitle(R.string.leaderboard)
        }
    }

    override fun onBindPresentationModel(view: View, pm: LeaderboardPm) {
        pm.users.observable bindTo { leaderboardAdapter.setItems(it) }
    }
}