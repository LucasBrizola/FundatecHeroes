package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.character.response.CharacterResponse
import com.example.fundatecheroes.databinding.ListItemBinding

class ListItemViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind (character: CharacterResponse){
        binding.tvUrl.text = character.image
        binding.tvNome.text = character.name
    }
}