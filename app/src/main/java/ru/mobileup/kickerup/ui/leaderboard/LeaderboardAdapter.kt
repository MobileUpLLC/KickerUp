package ru.mobileup.kickerup.ui.leaderboard

import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_leaderboard.view.*
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.domain.dto.User
import ru.mobileup.kickerup.extension.inflate
import ru.mobileup.kickerup.extension.visible
import ru.mobileup.kickerup.ui.common.list.BaseListAdapter

/**
 * @author Kulikov Konstantin
 * @since 08.07.2018.
 */
class LeaderboardAdapter(
    private val userSelected: (user: User) -> Unit = {}
) : BaseListAdapter<User, LeaderboardAdapter.ViewHolder>() {

    companion object {
        private const val FIRST_RATING = 0
        private const val SECOND_RATING = 1
        private const val THIRD_RATING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_leaderboard))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), BaseListAdapter.Bindable<User> {

        private lateinit var user: User

        init {
            itemView.setOnClickListener { userSelected.invoke(user) }
        }

        override fun bind(item: User) {
            this.user = item
            itemView.nameText.text = item.name
            itemView.pointsText.text = item.points.toString()

            when (adapterPosition) {
                FIRST_RATING -> showIcon(R.drawable.ic_star)
                SECOND_RATING -> showIcon(R.drawable.ic_rocket)
                THIRD_RATING -> showIcon(R.drawable.ic_bomb)
                else -> showRatingText(adapterPosition.toLong() + 1)
            }
        }

        private fun showRatingText(rating: Long) {
            itemView.ratingText.text = rating.toString()
            itemView.ratingText.visible(true)
            itemView.ratingIcon.visible(false)
        }

        private fun showIcon(@DrawableRes iconRes: Int) {
            itemView.ratingText.visible(false)
            itemView.ratingIcon.visible(true)
            itemView.ratingIcon.setImageResource(iconRes)
        }
    }
}