package com.example.fundatecheroes2.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fundatecheroes2.R
import com.example.fundatecheroes2.character.view.NewCharacterActivity
import com.example.fundatecheroes2.databinding.ActivityHomeBinding
import com.example.fundatecheroes2.navigation.BottomNavigationActivity
import com.example.fundatecheroes2.navigation.HomeFragment
import com.example.fundatecheroes2.navigation.NotificationFragment
import com.example.fundatecheroes2.navigation.ProfileFragment

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

        binding.btnBottomNavigation.setOnClickListener {
            startActivity(Intent(this@HomeActivity, BottomNavigationActivity::class.java))
        }
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.vpHome.adapter = adapter
        binding.tlHome.setupWithViewPager(binding.vpHome)
    }


    class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> {
                    return "Herói"
                }
                1 -> {
                    return "Vilão"
                }
            }
            return null
        }

        override fun getItem(position: Int): Fragment {
            return CharactersFragment.newInstance()
        }

    }
}