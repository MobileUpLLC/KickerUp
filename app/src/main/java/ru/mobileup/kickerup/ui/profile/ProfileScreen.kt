package ru.mobileup.kickerup.ui.profile

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.screen_profile.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class ProfileScreen : Screen<ProfilePm>(), RhombusController, BottomBarController {

    override val screenLayout: Int = R.layout.screen_profile

    override val rhombusX: Float = -630f

    override val rhombusY: Float = -550f

    private val gamesAdapter = GamesAdapter()

    override val showTabs: Boolean = true

    override fun providePresentationModel(): ProfilePm = StandAloneContext.koinContext.get()

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        with(view.gamesRecyclerView) {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(context!!)
            setHasFixedSize(true)
        }

        with(view.toolbar) {
            inflateMenu(R.menu.log_out)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.logOut -> passTo(presentationModel.logoutClicks.consumer)
                }
                true
            }
        }
    }

    override fun onBindPresentationModel(view: View, pm: ProfilePm) {
        pm.user.observable bindTo { user ->
            view.pointsText.text = user.points.toString()
            view.goalsText.text = user.goals.toString()
            view.gamesText.text = user.games.toString()
            view.toolbar.title = user.name
        }
        pm.games.observable bindTo { gamesAdapter.setItems(it) }
    }

    override fun onFubClickListener() {
        passTo(presentationModel.fabAction.consumer)
    }
}