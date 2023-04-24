package com.example.fundatecheroes.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityBottomNavigationBinding


class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setOnItemSelectedListener {
            onNavigationSelected(it)
            true
        }

        binding.navigation.selectedItemId = R.id.navigation_home
    }

    private fun onNavigationSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(HomeFragment.newInstance())
            }
            else -> {
                openFragment(NotificationFragment.newInstance())
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}