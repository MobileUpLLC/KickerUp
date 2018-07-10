package ru.mobileup.kickerup.domain.dto

/**
 * @author Kulikov Konstantin
 * @since 08.07.2018.
 *
 * @property id идентификатор
 * @property name имя пользователя
 * @property goals количество голов, забитых пользователем
 * @property games количество сыгранных игр
 * @property points очки пользователя
 */
data class User(
    val id: Long,
    val name: String,
    val goals: Int,
    val games: Int,
    val points: Float
)