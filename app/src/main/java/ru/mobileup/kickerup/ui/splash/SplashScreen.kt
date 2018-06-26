package ru.mobileup.kickerup.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.screen_splash.view.*
import org.koin.standalone.StandAloneContext
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.ui.common.RhombusController
import ru.mobileup.kickerup.ui.common.Screen


class SplashScreen : Screen<SplashPm>(), RhombusController {

    companion object {
        private const val SHOW_TITLE_DURATION = 1500L
    }

    override val rhombusX: Float = 0f

    override val rhombusY: Float = 0f

    override val screenLayout: Int = R.layout.screen_splash

    private val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
            // do nothing
        }

        override fun onAnimationEnd(p0: Animator?) {
            passTo(presentationModel.animationCanceled.consumer)
        }

        override fun onAnimationStart(p0: Animator?) {
            // do nothing
        }

        override fun onAnimationCancel(p0: Animator?) {
            // do nothing
        }
    }

    override fun onInitView(view: View, savedViewState: Bundle?) {
        super.onInitView(view, savedViewState)

        view.setOnClickListener { passTo(presentationModel.animationCanceled.consumer) }
    }

    override fun onBindPresentationModel(view: View, pm: SplashPm) {
        pm.startTextAnimation.observable bindTo {
            view.titleText.animate()
                .alpha(0f)
                .alphaBy(1f)
                .setDuration(SHOW_TITLE_DURATION)
                .setListener(animationListener)
                .start()
        }
    }

    override fun providePresentationModel(): SplashPm = StandAloneContext.koinContext.get()

}