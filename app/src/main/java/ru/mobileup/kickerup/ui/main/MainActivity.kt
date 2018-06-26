package ru.mobileup.kickerup.ui.main

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import kotlinx.android.synthetic.main.activity_main.*
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationMessageHandler
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.extension.*
import ru.mobileup.kickerup.ui.Back
import ru.mobileup.kickerup.ui.ShowAuthScreen
import ru.mobileup.kickerup.ui.ShowLeaderboardScreen
import ru.mobileup.kickerup.ui.ShowSplashScreen
import ru.mobileup.kickerup.ui.auth.AuthScreen
import ru.mobileup.kickerup.ui.common.BasePmActivity
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.leaderboard.LeaderboardScreen
import ru.mobileup.kickerup.ui.splash.SplashScreen

class MainActivity : BasePmActivity<MainPm>(), NavigationMessageHandler {

    override val activityLayout: Int = R.layout.activity_main

    override val containerId: Int = R.id.container

    override val rootScreen: Controller? = SplashScreen()

    override fun providePresentationModel(): MainPm = StandAloneContext.koinContext.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onBindPresentationModel(pm: MainPm) {

    }

    override fun handleNavigationMessage(message: NavigationMessage): Boolean {

        when(message) {
            is Back -> back()
            is ShowSplashScreen -> setPrimary(SplashScreen())
            is ShowAuthScreen -> setPrimary(AuthScreen())
            is ShowLeaderboardScreen -> setPrimary(LeaderboardScreen())
        }

        return true
    }

    private fun back() {
        if (!router.back()) {
            finish()
        } else {
            if (router.last() is RhombusController)
            attacheRhombusController(router.last() as RhombusController)
        }
    }

    private fun setPrimary(screen: Controller) {
        router.setRoot(screen)

        if (screen is RhombusController) {
            attacheRhombusController(screen)
        }
        if (screen is BottomBarController) {
            showBottomBar()
            attacheBottomBar(screen)
        } else {
            hideBottomBar()
            detachBottomBar()
        }
    }

    private fun navTo(screen: Controller) {
        router.goTo(screen)

        if (screen is RhombusController) {
            attacheRhombusController(screen)
        }
        if (screen is BottomBarController) {
            showBottomBar()
            attacheBottomBar(screen)
        } else {
            hideBottomBar()
            detachBottomBar()
        }
    }

    private fun attacheRhombusController(rhombusController: RhombusController) {
        rhombus.animate()
            .translationX(rhombusController.rhombusX)
            .translationY(rhombusController.rhombusY)
            .start()
    }

    private fun attacheBottomBar(bottomBarController: BottomBarController) {
        bottomBar.fabAlignmentMode = bottomBarController.fabAlignmentMode
        fab.setOnClickListener { bottomBarController.onFubClickListener().invoke() }
    }

    private fun detachBottomBar() {
        fab.setOnClickListener(null)
    }

    private fun hideBottomBar() {
        bottomBar.visible(false)
        fab.hide()
    }

    private fun showBottomBar() {
        bottomBar.visible(true)
        fab.show()
    }

}
