package com.example.fundatecheroes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginBinding
import com.example.fundatecheroes.presentation.LoginViewModel
import com.example.fundatecheroes.presentation.ViewState
import com.google.android.material.snackbar.Snackbar

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
                is
                ViewState.ShowErrorEmail -> toastEmailInvalido()
                ViewState.ShowErrorNull -> toastCamposNull()
                ViewState.ShowSuccess -> avancarTela()
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
        Toast.makeText(this, "Email deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun avancarTela() {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
    }

}