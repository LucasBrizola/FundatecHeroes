package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.databinding.ListItemBinding

class ListItemViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind (name: String){
        binding.tvName.text = name
    }
}