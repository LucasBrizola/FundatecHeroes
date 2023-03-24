package com.example.fundatecheroes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundatecheroes.databinding.FragmentCharactersBinding


class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListItemAdapter()
        binding.list.adapter = adapter

        adapter.setItems(list)
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}