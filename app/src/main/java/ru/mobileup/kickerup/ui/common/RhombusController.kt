package ru.mobileup.kickerup.ui.common


/**
 * Контроллер управления ромбом на главном экране
 */
interface RhombusController {

    /**
     * Первичная координата состояния
     */
    val rhombusX: Float

    /**
     * Первичная координата состояния
     */
    val rhombusY: Float

    /**
     * Время анимации перехода. Стандартная реализация равна 300 мс
     */
    val duration: Long
        get() = 300L

    /**
     * Вызов события изменения состояния
     */
    fun setOnRombusUpdater(updater: (x: Int, y: Int) -> Unit) = {
        // do nothing
    }
}