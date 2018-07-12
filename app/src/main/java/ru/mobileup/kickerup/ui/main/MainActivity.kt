package ru.mobileup.kickerup.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.bluelinelabs.conductor.Controller
import kotlinx.android.synthetic.main.activity_main.*
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationMessageHandler
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.extension.*
import ru.mobileup.kickerup.ui.*
import ru.mobileup.kickerup.ui.auth.AuthScreen
import ru.mobileup.kickerup.ui.common.BasePmActivity
import ru.mobileup.kickerup.ui.common.BottomBarController
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.leaderboard.LeaderboardScreen
import ru.mobileup.kickerup.ui.profile.ProfileScreen
import ru.mobileup.kickerup.ui.splash.SplashScreen
import ru.mobileup.kickerup.ui.startgame.StartGameScreen

class MainActivity : BasePmActivity<MainPm>(), NavigationMessageHandler {

    companion object {
        private const val LEADERBOARD_TAB = 0
        private const val PROFILE_TAB = 1
    }

    override val activityLayout: Int = R.layout.activity_main

    override val containerId: Int = R.id.container

    override val rootScreen: Controller? = SplashScreen()

    override fun providePresentationModel(): MainPm = StandAloneContext.koinContext.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_leaderboard))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_profile))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    LEADERBOARD_TAB -> handleNavigationMessage(ShowLeaderboardScreen())
                    PROFILE_TAB -> handleNavigationMessage(ShowProfileScreen())
                }
            }
        })
        bottomBar.setNavigationOnClickListener { handleNavigationMessage(Back()) }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onBindPresentationModel(pm: MainPm) {

    }

    override fun handleNavigationMessage(message: NavigationMessage): Boolean {

        // hide keyboard
        container.showKeyboard(false)

        when (message) {
            is Back -> back()
            is ShowSplashScreen -> setPrimary(SplashScreen())
            is ShowAuthScreen -> setPrimary(AuthScreen())
            is ShowLeaderboardScreen -> setPrimary(LeaderboardScreen())
            is ShowProfileScreen -> setPrimary(ProfileScreen())
            is ShowStartGameScreen -> navTo(StartGameScreen())
        }

        return true
    }

    private fun back() {
        if (!router.back()) {
            finish()
        } else {
            val screen = router.last()

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
        fab.setOnClickListener { bottomBarController.onFubClickListener() }

        if (bottomBarController.showBackButton) {
            bottomBar.setNavigationIcon(R.drawable.ic_arrow_back_black)
        } else {
            bottomBar.navigationIcon = null
        }

        tabLayout.visible(bottomBarController.showTabs)
    }

    private fun detachBottomBar() {
        fab.setOnClickListener(null)
    }

    private fun hideBottomBar() {
        bottomBar.visible(false)
        tabLayout.visible(false)
        fab.hide()
    }

    private fun showBottomBar() {
        tabLayout.visible(true)
        bottomBar.visible(true)
        fab.show()
    }

}
