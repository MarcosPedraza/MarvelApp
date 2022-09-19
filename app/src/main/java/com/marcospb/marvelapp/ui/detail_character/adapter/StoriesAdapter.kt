package com.marcospb.marvelapp.ui.detail_character.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marcospb.marvelapp.data.model.StoryItem
import com.marcospb.marvelapp.databinding.ItemHistoryBinding

class StoriesAdapter {


    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHistoryBinding.bind(itemView)

        fun bind(story: StoryItem) {
            binding.textView.text = story.name
        }
    }


    class STORY_ITEM_CALLBACK {

    }


}