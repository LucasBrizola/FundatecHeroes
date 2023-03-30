package com.example.fundatecheroes.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.character.presentation.Character
import com.example.fundatecheroes.databinding.ListItemBinding


class ListItemAdapter : RecyclerView.Adapter<ListItemViewHolder>() {

    private val list = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItems(characters: Character) {
        list.addAll(listOf(characters))
    }
}