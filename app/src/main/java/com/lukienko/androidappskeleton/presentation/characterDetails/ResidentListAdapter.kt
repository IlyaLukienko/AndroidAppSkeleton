package com.lukienko.androidappskeleton.presentation.characterDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.entity.Character
import kotlinx.android.synthetic.main.location_resident_recycler_cell.view.*

class ResidentListAdapter(private val residents: List<Character>) :
    RecyclerView.Adapter<ResidentListAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemViewType: Int): Holder {
        return Holder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.location_resident_recycler_cell,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.bind(residents[position])
    }

    override fun getItemCount(): Int {
        return residents.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.tvResidentName.text = character.name
        }
    }
}