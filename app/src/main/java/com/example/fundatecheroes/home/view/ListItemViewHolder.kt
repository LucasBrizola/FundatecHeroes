package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fundatecheroes.character.response.CharacterResponse
import com.example.fundatecheroes.databinding.ListItemBinding

class ListItemViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind (character: CharacterResponse){
        Glide.with(itemView.context)
            .load(character.image)
            .into(binding.tvImage)
        binding.tvNome.text = character.name
    }
}