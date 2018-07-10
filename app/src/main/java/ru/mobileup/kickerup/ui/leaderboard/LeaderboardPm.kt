package ru.mobileup.kickerup.ui.leaderboard

import io.reactivex.functions.Consumer
import ru.mobileup.kickerup.domain.GetLeaderboardInteractor
import ru.mobileup.kickerup.domain.dto.User
import ru.mobileup.kickerup.ui.common.ScreenPm
import timber.log.Timber


class LeaderboardPm(
    private val getLeaderboardInteractor: GetLeaderboardInteractor
) : ScreenPm() {

    val users = State<List<User>>(emptyList())

    val userSelected = Action<User>()

    override fun onCreate() {
        super.onCreate()

        getLeaderboardInteractor.execute()
            .subscribe(users.consumer, Consumer { Timber.e(it) })
            .untilDestroy()
    }
}