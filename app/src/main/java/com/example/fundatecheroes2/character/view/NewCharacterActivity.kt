package com.example.fundatecheroes2.character.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.fundatecheroes2.R
import com.example.fundatecheroes2.character.presentation.NewCharacterViewModel
import com.example.fundatecheroes2.character.presentation.ViewState
import com.example.fundatecheroes2.databinding.ActivityNewCharacterBinding
import com.fundatec.components.showSnackbar

class NewCharacterActivity : AppCompatActivity(), OnItemSelectedListener {

    //var criada para armazenar o herói vilão e usar na hora de responder que foi um sucesso
    private var hv = ""

    private var courses = arrayOf("Herói ou Vilão?", "Herói", "Vilão")

    private lateinit var binding: ActivityNewCharacterBinding

    private val viewModel: NewCharacterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        criarSpinner()
        configActionBar()

        configSaveButton()


        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                ViewState.ShowErrorNull -> showSnackbar(binding.root, "preencha todos os campos!")
                ViewState.ShowErrorUrl -> showSnackbar(binding.root, "URL deve ser válido! (ter um @)")
                ViewState.ShowErrorDate -> showSnackbar(binding.root, "Data está no formato inválido! (diferente de dd/mm/aaaa)")
                ViewState.ShowErrorHeroiVilao -> showSnackbar(binding.root, "escolha entre herói ou vilão")
                is ViewState.ShowSuccess -> showSnackbar(binding.root, hv + "salvo")
            }
        }
    }


    private fun configActionBar() {
        setSupportActionBar(binding.tbNavigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configSaveButton() {
        binding.btnSalvar.setOnClickListener {
            viewModel.validarCampos(
                nome = binding.nome.text.toString(),
                url = binding.url.text.toString(),
                descricao = binding.descricao.text.toString(),
                heroiVilao = binding.spinnerHeroiVilao.getSelectedItem().toString(),
                idade = binding.idade.text.toString(),
                aniversario = binding.aniversario.text.toString(),
            )
            hv = binding.spinnerHeroiVilao.getSelectedItem().toString()
        }
    }

    private fun criarSpinner() {
        binding.spinnerHeroiVilao.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            R.layout.spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerHeroiVilao.adapter = ad
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View, position: Int,
        id: Long
    ) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}