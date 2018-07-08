package ru.mobileup.kickerup.ui.leaderboard

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_leaderboard.view.*
import ru.mobileup.kickerup.R
import ru.mobileup.kickerup.domain.dto.User
import ru.mobileup.kickerup.extension.inflate
import ru.mobileup.kickerup.ui.common.list.BaseListAdapter

/**
 * @author Kulikov Konstantin
 * @since 08.07.2018.
 */
class LeaderboardAdapter(
    private val userSelected: (user: User) -> Unit = {}
) : BaseListAdapter<User, LeaderboardAdapter.ViewHolder>() {

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
        }
    }
}