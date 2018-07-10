package ru.mobileup.kickerup.ui.profile

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_game.view.*
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.domain.dto.Game
import ru.mobileup.kickerup.extension.inflate
import ru.mobileup.kickerup.ui.common.list.BaseListAdapter


class GamesAdapter : BaseListAdapter<Game, GamesAdapter.ViewHolder>() {

    companion object {
        private const val ACCENT_GAMES_COUNT = 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.item_game))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), BaseListAdapter.Bindable<Game> {

        override fun bind(item: Game) {
            itemView.goalsText.text = itemView.resources.getString(R.string.common_goals, item.totalFirstCommandGoals, item.totalSecondCommandGoals)

            val color = if (adapterPosition < ACCENT_GAMES_COUNT) {
                itemView.resources.getColor(R.color.text_color_primary)
            } else {
                itemView.resources.getColor(R.color.black_translucent_54)
            }

            itemView.goalsText.setTextColor(color)
        }
    }
}