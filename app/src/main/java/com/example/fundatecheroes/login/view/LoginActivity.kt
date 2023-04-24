package com.example.fundatecheroes.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

import com.example.fundatecheroes.databinding.ActivityLoginBinding
import com.example.fundatecheroes.home.view.HomeActivity
import com.example.fundatecheroes.login.presentation.ViewState
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.profile.view.ProfileActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configLoginButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewState.ShowErrorEmail -> toastEmailInvalido()
                is ViewState.ShowErrorNull -> toastCamposNull()
                is ViewState.ShowErrorUserNull -> toastUsuarioNull()
                is ViewState.ShowSuccess -> avancarTela()
                is ViewState.Loading -> loading()
            }
        }


        binding.btnNovo.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
        }

    }

    private fun configLoginButton() {
        binding.btnLogin.setOnClickListener {
            viewModel.validarEmailESenha(
                email = binding.etEmail.text.toString(),
                password = binding.etSenha.text.toString(),
            )
        }
    }

    private fun toastEmailInvalido() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "Email deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun toastUsuarioNull() {
        binding.pbLoading.isVisible = false
        Toast.makeText(this, "usuário não existe!", Toast.LENGTH_LONG).show()
    }

    private fun avancarTela() {
        binding.pbLoading.isVisible = false
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun loading() {
        binding.pbLoading.isVisible = true
    }

}