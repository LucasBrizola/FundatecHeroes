package com.example.fundatecheroes.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fundatecheroes.character.response.CharacterResponse
import com.example.fundatecheroes.character.view.NewCharacterActivity
import com.example.fundatecheroes.databinding.ActivityHomeBinding
import com.example.fundatecheroes.navigation.BottomNavigationActivity

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configActionBar()
        configTab()

        binding.btnNovoPersonagem.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NewCharacterActivity::class.java))
        }

        binding.btnBottomNavigation.setOnClickListener{
            startActivity(Intent(this@HomeActivity, BottomNavigationActivity::class.java))
        }
    }

    private fun configTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.vpHome.adapter = adapter
        binding.tlHome.setupWithViewPager(binding.vpHome)
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "Heroi"
            1 -> "Vilao"
            else -> {
                null
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        Log.e("CharacterDataSource", "posição: " + "${position}")
        val characterType = when (position) {
            0 -> CharacterResponse.HeroVillain.HERO
            else -> CharacterResponse.HeroVillain.VILLAIN
        }

        return CharacterFragment.newInstance(characterType)
    }

}