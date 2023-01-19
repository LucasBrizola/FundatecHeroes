package com.example.fundatecheroes.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginBinding
import com.example.fundatecheroes.home.view.HomeActivity
import com.example.fundatecheroes.login.view.presentation.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener{
            validarEmailValido()
        }

        binding.btnNovo.setOnClickListener{
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
        }

        viewModel.newText.observe(this){
        }

    }

    private fun validarEmailValido() {
        if(!binding.email.text.toString().contains("@")) {
            toastEmailValido()
        }
        else {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
    }

    private fun toastEmailValido() {
        Toast.makeText(this, "Email deve ser válido! (ter um @)", Toast.LENGTH_SHORT).show()
    }

    private fun showSnack () {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "Nosso Snackbar", Snackbar.LENGTH_LONG)
            .setAction("Ok") {
                val intent = Intent (this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
            .show()
    }
}