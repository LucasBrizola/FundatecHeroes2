package com.example.fundatecheroes2.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes2.R
import com.example.fundatecheroes2.character.view.NewCharacterActivity
import com.example.fundatecheroes2.databinding.ActivityHomeBinding
import com.example.fundatecheroes2.navigation.BottomNavigationActivity

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configActionBar()

        binding.btnNovoPersonagem.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NewCharacterActivity::class.java))
        }

        binding.btnBottomNavigation.setOnClickListener{
            startActivity(Intent(this@HomeActivity, BottomNavigationActivity::class.java))
        }
    }

    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}