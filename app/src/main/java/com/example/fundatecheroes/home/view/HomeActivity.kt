package com.example.fundatecheroes.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fundatecheroes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configActionBar()
        configTab()
    }

    private fun configTab() {
        val adapter = viewPagerAdapter(supportFragmentManager)
        binding.vpHome.adapter = adapter
        binding.tlHome.setupWithViewPager(binding.vpHome)
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class viewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab ${position.inc()}"
        }

        override fun getItem(position: Int): Fragment {
            return CharactersFragment.newInstance(position.inc().toString())
        }

    }

}