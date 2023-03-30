package com.example.fundatecheroes2.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fundatecheroes2.databinding.ActivityLoginBinding
import com.example.fundatecheroes2.home.HomeActivity
import com.example.fundatecheroes2.profile.view.ProfileActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configLoginButton()


        binding.btnNovo.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
        }

    }

    private fun configLoginButton() {
        binding.btnLogin.setOnClickListener {
            avancarTela()
        }
    }

    private fun toastEmailInvalido() {
        Toast.makeText(this, "Email deve ser válido! (ter um @)", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "campos não podem ser vazios!", Toast.LENGTH_LONG).show()
    }

    private fun avancarTela() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

}