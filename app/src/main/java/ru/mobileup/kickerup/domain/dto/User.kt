package ru.mobileup.kickerup.domain.dto

/**
 * @author Kulikov Konstantin
 * @since 08.07.2018.
 */
data class User(
    val id: Long,
    val name: String,
    val goals: Int,
    val games: Int,
    val points: Int
)