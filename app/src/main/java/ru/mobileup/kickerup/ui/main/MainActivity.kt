package ru.mobileup.kickerup.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationMessageHandler
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.auth.AuthScreen
import ru.mobileup.kickerup.ui.common.BasePmActivity

class MainActivity : BasePmActivity<MainPm>(), NavigationMessageHandler {

    override val activityLayout: Int = R.layout.activity_main

    override val containerId: Int = R.id.container

    override val rootScreen: Controller? = AuthScreen()

    override fun providePresentationModel(): MainPm = StandAloneContext.koinContext.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onBindPresentationModel(pm: MainPm) {

    }

    override fun handleNavigationMessage(message: NavigationMessage): Boolean {
        return true
    }

}
