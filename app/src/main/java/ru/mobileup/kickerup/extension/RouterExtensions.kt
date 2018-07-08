package ru.mobileup.kickerup.extension

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import ru.mobileup.kickerup.ui.common.DialogController

/**
 * Extensions to made navigation easy.
 * @author Dmitriy Gorbunov
 */

fun Router.goTo(screen: Controller, withAnimation: Boolean = true) {
    pushController(
            RouterTransaction.with(screen)
                .apply {
                    if (withAnimation) {
                        popChangeHandler(FadeChangeHandler())
                        pushChangeHandler(FadeChangeHandler())
                    }
                }
                .tag(screen::class.java.name)
    )
}

fun Router.setRoot(screen: Controller, withAnimation: Boolean = false) {
    setRoot(
            RouterTransaction.with(screen)
                .apply {
                    if (withAnimation) {
                        popChangeHandler(FadeChangeHandler())
                        pushChangeHandler(FadeChangeHandler())
                    }
                }
                .tag(screen::class.java.name)
    )
}

fun Router.back(): Boolean {
    return if (backstackSize > 1) {
        popCurrentController()
        true
    } else {
        false
    }
}

inline fun <reified T : Controller> Router.backTo() {
    if (backstackSize > 1) {
        popToTag(T::class.java.name)
    }
}

inline fun <reified T : Controller> Router.find(): T? {
    return getControllerWithTag(T::class.java.name) as? T
}

inline fun Router.last(): Controller = this.backstack.last().controller()

fun Router.showDialog(dialog: DialogController) {
    getControllerWithTag(dialog::class.java.name)?.let { popController(it) }
    dialog.showDialog(this, dialog::class.java.name)
}