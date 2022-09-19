package com.marcospb.marvelapp.ui.list_character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcospb.marvelapp.R
import com.marcospb.marvelapp.data.model.CharacterItem
import com.marcospb.marvelapp.databinding.ItemCharacterBinding

class CharacterListAdapter(private val onCharacterClick: (character: CharacterItem) -> Unit) :
    ListAdapter<CharacterItem, CharacterListAdapter.CharacterViewHolder>(CHARACTER_ITEM_CALLBACK()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCharacterBinding.bind(itemView)


        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = getItem(adapterPosition)
                    onCharacterClick(item)
                }
            }
        }

        fun bind(item: CharacterItem) {
            Glide.with(itemView.context)
                .load("${item.thumbnail?.path}.${item.thumbnail?.extension}")
                .placeholder(R.drawable.ic_hide_image)
                .into(binding.imageView)
            binding.name.text = item.name

        }

    }


    class CHARACTER_ITEM_CALLBACK : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem == newItem
        }


    }


}