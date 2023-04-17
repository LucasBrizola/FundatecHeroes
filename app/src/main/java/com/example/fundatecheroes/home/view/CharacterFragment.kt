package com.example.fundatecheroes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.fundatecheroes.character.response.CharacterResponse
import com.example.fundatecheroes.databinding.FragmentCharactersBinding
import com.example.fundatecheroes.home.presentation.CharacterFragmentViewModel
import com.example.fundatecheroes.home.presentation.ViewState


class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val adapter by lazy {
        ListItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = CharacterFragmentViewModel()
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        val bundle = arguments?.getString("heroType")
        viewModel.populateRecyclerView(CharacterResponse.HeroVillain.valueOf(bundle.orEmpty()))
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.ShowCharacterList -> addItems(state.characterList)
                is ViewState.ShowListEmpty -> recyclerVazio()
                is ViewState.Loading -> loading()
            }
        }
    }

    private fun loading() {
        binding.pbLoading.isVisible = true
    }

    private fun addItems(characterList: List<CharacterResponse>) {
        binding.pbLoading.isVisible = false
        adapter.setItems(characterList)
    }

    private fun recyclerVazio() {
        binding.pbLoading.isVisible = false
        Toast.makeText(requireContext(), "Recycler está vazio.", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(hv: CharacterResponse.HeroVillain): Fragment {
            return CharacterFragment().apply {
                val bundle = Bundle()
                bundle.putString("heroType", hv.name)
                arguments = bundle
            }
        }
    }
}