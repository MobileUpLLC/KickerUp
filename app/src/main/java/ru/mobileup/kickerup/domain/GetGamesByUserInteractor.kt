package ru.mobileup.kickerup.domain

import ru.mobileup.kickerup.domain.dto.Game
import ru.mobileup.kickerup.domain.dto.User
import java.util.*


class GetGamesByUserInteractor {

    fun execute(user: User? = null) = (0..13).map {
        Game(
                0,
                emptyList(),
                emptyList(),
                user ?: User(0, "Василий", 0, 0, 12.5F),
                listOf(
                        3 to 1,
                        1 to 3,
                        1 to 1
                ),
                5,
                5,
                Date(),
                Date(),
                Game.Status.ENDED
        )
    }
}