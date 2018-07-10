package ru.mobileup.kickerup.domain

import io.reactivex.Single
import ru.mobileup.kickerup.domain.dto.User


class GetCurrentUserInteractor {

    fun execute() = Single.just(User(
            0,
            "Василий",
            52,
            10,
            35F
    ))
}