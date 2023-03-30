package com.example.fundatecheroes.home.view

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fundatecheroes.App
import com.example.fundatecheroes.character.presentation.Character
import com.example.fundatecheroes.databinding.FragmentCharactersBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = App.context.getSharedPreferences("bd", MODE_PRIVATE)

        val characterString = preferences.getString("character", "")
        if (characterString.isNullOrBlank()) {
            toastRecyclerVazio()
        }
        else{
            val characterFromPreferences = moshi
                .adapter(Character::class.java)
                .fromJson(characterString)

            val adapter = ListItemAdapter()
            binding.list.adapter = adapter

            adapter.setItems(characterFromPreferences!!)
        }

    }
    private fun toastRecyclerVazio() {
        Toast.makeText(requireContext(), "Recycler está vazio.", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}