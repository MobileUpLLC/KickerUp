package ru.mobileup.kickerup.ui.common


/**
 * Контроллер управления ромбом на главном экране
 */
interface RhombusController {

    /**
     * Первичная координата состояния
     */
    val rhombusX: Int

    /**
     * Первичная координата состояния
     */
    val rhombusY: Int

    /**
     * Вызов события изменения состояния
     */
    fun setOnRombusUpdater(updater: (x: Int, y: Int) -> Unit)
}