package ru.mobileup.kickerup.ui.main

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationMessageHandler
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.extension.back
import ru.mobileup.kickerup.extension.goTo
import ru.mobileup.kickerup.extension.setRoot
import ru.mobileup.kickerup.ui.Back
import ru.mobileup.kickerup.ui.ShowAuthScreen
import ru.mobileup.kickerup.ui.ShowSplashScreen
import ru.mobileup.kickerup.ui.auth.AuthScreen
import ru.mobileup.kickerup.ui.common.BasePmActivity
import ru.mobileup.kickerup.ui.common.RhombusController
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
            is ShowAuthScreen -> navTo(AuthScreen())
            is ShowSplashScreen -> setPrimary(SplashScreen())
        }

        return true
    }

    private fun back() {
        if (!router.back()) {
            finish()
        }
    }

    private fun setPrimary(screen: Controller) {
        router.setRoot(screen)

        if (screen is RhombusController) {
            attacheRhombusController(screen)
        }
    }

    private fun navTo(screen: Controller) {
        router.goTo(screen)

        if (screen is RhombusController) {
            attacheRhombusController(screen)
        }
    }

    private fun attacheRhombusController(rhombusController: RhombusController) {

    }

}
