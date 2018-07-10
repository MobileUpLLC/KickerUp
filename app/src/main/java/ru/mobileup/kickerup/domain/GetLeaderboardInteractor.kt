package ru.mobileup.kickerup.domain

import io.reactivex.Single
import ru.mobileup.kickerup.domain.dto.User

/**
 * @author Kulikov Konstantin
 * @since 08.07.2018.
 */
class GetLeaderboardInteractor {

    fun execute() = Single.just(
            (0..15).map {
                User(
                        it.toLong(),
                        "Вася $it",
                        0,
                        0,
                        12.5F
                )
            }
    )
}