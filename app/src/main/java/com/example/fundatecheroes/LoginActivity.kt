package com.example.fundatecheroes

import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class LoginActivity {

    setContentView(R.layout.activitiy_login)

    val container = findViewById<ConstraintLayout>(R.id.container)

    val tvText = findViewById<TextView>(R.id.tv_text).apply {
        setOnClickListener {
            Snackbar
                .make(container,"Nosso Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Ok") {
                    val intent = Intent{ this@LoginActivity, HomeActivity::class.java}
                    startActivity(intent)
                }
                .show()
        }
    }

    viewModel.netText.observe(this) { newText ->
    }
}