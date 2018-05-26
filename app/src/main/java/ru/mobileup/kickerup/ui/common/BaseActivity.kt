package ru.mobileup.kickerup.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import ru.mobileup.kickerup.extension.setRoot
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


abstract class BaseActivity : AppCompatActivity() {

    protected abstract val activityLayout: Int
    protected abstract val containerId: Int

    protected lateinit var router: Router

    protected abstract val rootScreen: Controller?

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        router = Conductor.attachRouter(this, findViewById(containerId), savedInstanceState)

        rootScreen?.let { root ->
            if (!router.hasRootController()) {
                router.setRoot(root)
            }
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack() ) {
            super.onBackPressed()
        }
    }
}