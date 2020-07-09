package com.lukienko.androidappskeleton.presentation.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.character_recycler_cell.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharactersAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.Holder>(), KoinComponent {

    private val imageLoader: ImageLoader by inject()
    var onItemClick: ((character: Character, imageView: ImageView) -> Unit)? = null

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
        viewHolder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.tvCharacterName.text = character.name
            itemView.tvCharacterGender.text = character.gender
            itemView.tvCharacterStatus.text = character.status
            character.image?.let { imageLoader.loadCircleAvatar(itemView.ivCharacterAvatar, it) }
            itemView.setOnClickListener {
                itemView.ivCharacterAvatar.transitionName = character.id.toString()
                onItemClick?.invoke(character, itemView.ivCharacterAvatar)
            }
        }
    }
}