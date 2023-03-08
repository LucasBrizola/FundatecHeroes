package com.example.fundatecheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes.login.view.LoginActivity
import com.example.fundatecheroes.profile.view.ProfileActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
        finish()
    }
}