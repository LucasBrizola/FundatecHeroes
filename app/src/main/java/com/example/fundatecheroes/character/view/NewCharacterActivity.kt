package com.example.fundatecheroes.character.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.fundatecheroes.R
import com.example.fundatecheroes.character.presentation.NewCharacterViewModel
import com.example.fundatecheroes.character.presentation.ViewState
import com.example.fundatecheroes.databinding.ActivityNewCharacterBinding
import com.example.fundatecheroes.home.view.HomeActivity

class NewCharacterActivity : AppCompatActivity(), OnItemSelectedListener {

    private var courses = arrayOf<String?>("Herói ou Vilão? v", "HERO", "VILLAIN")

    private lateinit var binding: ActivityNewCharacterBinding

    private val viewModel: NewCharacterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        criarSpinner()

        configSaveButton()



        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewState.ShowErrorNull -> toastCamposNull()
                is ViewState.ShowErrorHeroiVilao -> toastEscolhaHeroiVilao()
                is ViewState.Loading -> loading()
                is ViewState.ShowSuccess -> salvar()
            }
        }
    }

    private fun configSaveButton() {
        binding.btnSalvar.setOnClickListener {
            viewModel.validarCampos(
                name = binding.nome.text.toString(),
                url = binding.image.text.toString(),
                description = binding.descricao.text.toString(),
                heroiVilao = binding.spinnerHeroiVilao.getSelectedItem().toString(),
                age = binding.idade.text.toString(),
                birthday = binding.aniversario.text.toString(),
            )
        }
    }

    private fun criarSpinner() {
        binding.spinnerHeroiVilao.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            R.layout.spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerHeroiVilao.adapter = ad
    }

    private fun loading() {
        binding.pbLoading.isVisible = true
    }

    private fun toastCamposNull() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "preencha todos os campos!", Toast.LENGTH_LONG).show()
    }

    private fun toastEscolhaHeroiVilao() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "escolha entre herói ou vilão", Toast.LENGTH_LONG).show()
    }

    private fun salvar() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "Heroi/Vilão salvo!", Toast.LENGTH_LONG).show()
        avancarTela()
    }

    private fun avancarTela() {
        finish()
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View, position: Int,
        id: Long
    ) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}