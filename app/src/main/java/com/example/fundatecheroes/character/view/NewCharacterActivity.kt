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
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class NewCharacterActivity : AppCompatActivity(), OnItemSelectedListener {

    private var courses = arrayOf<String?>("Herói", "Vilão")

    private lateinit var binding: ActivityNewCharacterBinding

    private val viewModel: NewCharacterViewModel by viewModels()

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        criarSpinner()

        configSaveButton()


        val characterString = moshi.adapter(Character::class.java)
            .toJson(character)

        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                ViewState.ShowErrorNull -> toastCamposNull()
                ViewState.ShowErrorUrl -> toastUrlInvalida()
                ViewState.ShowSuccess -> salvar()
            }
        }
    }

    private fun configSaveButton() {
        binding.btnSalvar.setOnClickListener {
            viewModel.validarCampos(
                nome = binding.nome.text.toString(),
                url = binding.url.text.toString(),
                descricao = binding.descricao.text.toString(),
                heroiVilao = binding.SpinnerHeroiVilao.onItemSelectedListener.toString(),
                idade = binding.nome.text.toString(),
                aniversario = binding.url.text.toString(),
            )
        }
    }

    private fun criarSpinner() {
        binding.SpinnerHeroiVilao.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.SpinnerHeroiVilao.adapter = ad
    }

    private fun toastUrlInvalida() {
        Toast.makeText(this, "URL deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun salvar(characterString: String?) {
        Toast.makeText(this, "Heroi/Vilão salvo!", Toast.LENGTH_LONG).show()

        val preferences = getSharedPreferences("bd", MODE_PRIVATE)

        preferences.edit().putString("character", characterString).commit()
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View, position: Int,
        id: Long
    ) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}