package com.example.fundatecheroes.character.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fundatecheroes.R
import com.example.fundatecheroes.character.presentation.NewCharacterViewModel
import com.example.fundatecheroes.character.presentation.ViewState
import com.example.fundatecheroes.databinding.ActivityNewCharacterBinding

class NewCharacterActivity : AppCompatActivity(), OnItemSelectedListener {

    private var courses = arrayOf<String?>("Herói ou Vilão?", "Herói", "Vilão")

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
                is
                ViewState.ShowErrorNull -> toastCamposNull()
                ViewState.ShowErrorUrl -> toastUrlInvalida()
                ViewState.ShowErrorDate -> toastDataInvalida()
                ViewState.ShowErrorHeroiVilao -> toastEscolhaHeroiVilao()
                is ViewState.ShowSuccess -> salvar()
            }
        }
    }

    private fun configSaveButton() {
        binding.btnSalvar.setOnClickListener {
            viewModel.validarCampos(
                nome = binding.nome.text.toString(),
                url = binding.url.text.toString(),
                descricao = binding.descricao.text.toString(),
                heroiVilao = binding.spinnerHeroiVilao.getSelectedItem().toString(),
                idade = binding.idade.text.toString(),
                aniversario = binding.aniversario.text.toString(),
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

    private fun toastUrlInvalida() {
        Toast.makeText(this, "URL deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }


    private fun toastDataInvalida() {
        Toast.makeText(this, "Data está no formato inválido! (diferente de dd/mm/aaaa)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "preencha todos os campos!", Toast.LENGTH_LONG).show()
    }

    private fun toastEscolhaHeroiVilao() {
        Toast.makeText(this, "escolha entre herói ou vilão", Toast.LENGTH_LONG).show()
    }

    private fun salvar() {
        Toast.makeText(this, "Heroi/Vilão salvo!", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View, position: Int,
        id: Long
    ) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}