package com.example.fundatecheroes2.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fundatecheroes.login.view.presentation.LoginViewModel
import com.example.fundatecheroes.login.view.presentation.ViewState
import com.example.fundatecheroes2.databinding.ActivityLoginBinding
import com.example.fundatecheroes2.home.HomeActivity
import com.example.fundatecheroes2.profile.view.ProfileActivity
import com.fundatec.components.test

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configLoginButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                ViewState.ShowErrorEmail -> toastEmailInvalido()
                ViewState.ShowErrorNull -> toastCamposNull()
                ViewState.ShowSuccess -> avancarTela()
            }
        }


        binding.btnNovo.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
        }

    }

    private fun configLoginButton() {
        binding.btnLogin.setOnClickListener {
            viewModel.validarEmailESenha(
                email = binding.etEmail.text.toString(),
                password = binding.etSenha.text.toString(),
            )
        }
    }

    private fun toastEmailInvalido() {
        Toast.makeText(this, "Email deve ser válido (ter um @).", Toast.LENGTH_LONG).show()
    }

    private fun toastCamposNull() {
        Toast.makeText(this, "campos não podem ser vazios", Toast.LENGTH_LONG).show()
    }

    private fun avancarTela() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

}