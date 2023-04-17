package com.example.fundatecheroes.profile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.fundatecheroes.databinding.ActivityProfileBinding
import com.example.fundatecheroes.profile.presentation.ProfileViewModel
import com.example.fundatecheroes.profile.presentation.ViewState

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configActionBar()
        configSaveButton()

        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewState.ShowErrorEmail -> toastEmailInvalido()
                is ViewState.ShowErrorNull -> toastCamposNull()
                is ViewState.ShowSuccess -> showSuccess()
                is ViewState.Loading -> OnLoading()
            }
        }

    }

    private fun configSaveButton() {
        binding.btnNewUser.setOnClickListener {
            viewModel.validateAndSaveUser(
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etSenha.text.toString(),
            )
        }
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun toastEmailInvalido() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "Email deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun showSuccess() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "usuário salvo", Toast.LENGTH_LONG).show()
    }

    private fun OnLoading() {
        binding.pbLoading.isVisible = true
    }
}