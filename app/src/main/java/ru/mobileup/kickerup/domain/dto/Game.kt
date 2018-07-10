package ru.mobileup.kickerup.domain.dto

import ru.mobileup.kickerup.domain.dto.Game.Status
import java.util.*

/**
 * @author Kulikov Konstantin
 *
 * @property id идентификатор игры
 * @property firstCommandPlayers участники первой команды
 * @property secondCommandPlayers участники второй команды
 * @property owner создатель игры
 * @property rounds игровые раунды. Это [Pair], где первое значение - голы первой команды. Второе - второй
 * @property totalFirstCommandGoals все голы первой команды
 * @property totalSecondCommandGoals все голы второй команды
 * @property start время начала игры
 * @property end время конца игры
 * @property start [Status] состояние игры
 */
data class Game(
    val id: Long,
    val firstCommandPlayers: List<User>,
    val secondCommandPlayers: List<User>,
    val owner: User,
    val rounds: List<Pair<Int, Int>>,
    val totalFirstCommandGoals: Int,
    val totalSecondCommandGoals: Int,
    val start: Date,
    val end: Date,
    val status: Status
) {
    enum class Status{
        WAITING,
        IN_GAME,
        ENDED
    }
}