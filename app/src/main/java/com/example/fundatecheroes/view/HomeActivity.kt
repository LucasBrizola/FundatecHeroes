package com.example.fundatecheroes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configActionBar()
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}