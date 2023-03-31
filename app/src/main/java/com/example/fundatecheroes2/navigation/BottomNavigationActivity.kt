package com.example.fundatecheroes2.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fundatecheroes2.R
import com.example.fundatecheroes2.databinding.ActivityBottomNavigationBinding

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
            R.id.navigation_profile -> {
                openFragment(ProfileFragment.newInstance())
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
