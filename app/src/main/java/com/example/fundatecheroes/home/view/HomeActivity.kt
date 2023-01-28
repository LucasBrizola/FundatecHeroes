package com.example.fundatecheroes.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes.R
import com.example.fundatecheroes.character.view.NewCharacterActivity
import com.example.fundatecheroes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lateinit var binding : ActivityHomeBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNovoPersonagem.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NewCharacterActivity::class.java))
        }


    }
}