package com.example.fundatecheroes.character.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fundatecheroes.character.presentation.NewCharacterViewModel
import com.example.fundatecheroes.character.presentation.ViewState
import com.example.fundatecheroes.databinding.ActivityNewCharacterBinding

class NewCharacterActivity : AppCompatActivity(), OnItemSelectedListener {

    private var courses = arrayOf<String?>("DC", "Marvel")

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
                ViewState.ShowSuccess -> toastSalvar()
            }
        }
    }

    private fun configSaveButton() {
        binding.btnSalvar.setOnClickListener {
            viewModel.validarCampos(
                nome = binding.nome.text.toString(),
                url = binding.url.text.toString(),
                descricao = binding.descricao.text.toString(),
                DcMarvel = binding.SpinnerDcMarvel.onItemSelectedListener.toString(),
                idade = binding.nome.text.toString(),
                aniversario = binding.url.text.toString(),
            )
        }
    }

    private fun criarSpinner(){
        binding.SpinnerDcMarvel.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.SpinnerDcMarvel.adapter = ad
    }

    private fun toastUrlInvalida() {
        Toast.makeText(this, "URL deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun toastSalvar() {
        //falta salvar
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View, position: Int,
        id: Long
    ) {}

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}