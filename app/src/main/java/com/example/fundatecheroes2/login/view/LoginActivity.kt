package com.example.fundatecheroes2.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fundatecheroes.login.view.presentation.LoginViewModel
import com.example.fundatecheroes.login.view.presentation.ViewState
import com.example.fundatecheroes2.databinding.ActivityLoginBinding
import com.example.fundatecheroes2.home.HomeActivity
import com.example.fundatecheroes2.profile.view.ProfileActivity
import com.fundatec.components.showSnackbar

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
                ViewState.ShowErrorEmail -> showSnackbar(binding.root,"Email deve ser válido (ter um @).")
                ViewState.ShowErrorNull -> showSnackbar(binding.root, "campos não podem ser vazios!")
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

    private fun avancarTela() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

}