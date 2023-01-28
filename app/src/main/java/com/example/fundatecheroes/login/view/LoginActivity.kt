package com.example.fundatecheroes.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fundatecheroes.databinding.ActivityLoginBinding
import com.example.fundatecheroes.home.view.HomeActivity
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.ViewState
import com.example.fundatecheroes.profile.view.ProfileActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class LoginActivity : AppCompatActivity() {

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val character by lazy {
        //Character("Batman", 40)
    }

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val preferences = getSharedPreferences("bd", MODE_PRIVATE)
        val characterString = moshi.adapter(Character::class.java).toJson(character)
        preferences.edit().putString("character", characterString).commit()*/


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