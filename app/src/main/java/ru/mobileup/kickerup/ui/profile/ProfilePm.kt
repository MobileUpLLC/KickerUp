package ru.mobileup.kickerup.ui.profile

import io.reactivex.functions.Consumer
import ru.mobileup.kickerup.domain.GetCurrentUserInteractor
import ru.mobileup.kickerup.domain.GetGamesByUserInteractor
import ru.mobileup.kickerup.domain.dto.Game
import ru.mobileup.kickerup.domain.dto.User
import ru.mobileup.kickerup.ui.common.ScreenPm
import timber.log.Timber


class ProfilePm(
    private val getCurrentUserInteractor: GetCurrentUserInteractor,
    private val getGamesByUserInteractor: GetGamesByUserInteractor
): ScreenPm() {

    val user = State<User>()
    val games = State<List<Game>>()
    val logoutClicks = Action<Unit>()

    override fun onCreate() {
        super.onCreate()

        getCurrentUserInteractor.execute()
            .flatMap {
                user.consumer.accept(it)
                getGamesByUserInteractor.execute(it)
            }
            .subscribe(games.consumer, Consumer {
                Timber.e(it)
            })
            .untilDestroy()

        logoutClicks.observable
            .subscribe { }
            .untilDestroy()
    }
}