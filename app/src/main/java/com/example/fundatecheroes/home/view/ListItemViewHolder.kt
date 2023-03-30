package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.character.presentation.Character
import com.example.fundatecheroes.databinding.ListItemBinding

class ListItemViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind (character: Character){
        binding.tvUrl.text = character.url
        binding.tvNome.text = character.nome
    }
}