package com.lukienko.androidappskeleton.presentation.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.character_recycler_cell.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharactersAdapter(private val users: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.Holder>(), KoinComponent {

    private val imageLoader: ImageLoader by inject()
    var onItemClick: ((character: Character) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemViewType: Int): Holder {
        return Holder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.character_recycler_cell,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.tvCharacterName.text = character.name
            itemView.tvCharacterGender.text = character.gender
            itemView.tvCharacterStatus.text = character.status
            character.image?.let { imageLoader.loadCircleAvatar(itemView.ivCharacterAvatar, it) }
            itemView.setOnClickListener {
                onItemClick?.invoke(character)
            }
        }
    }
}