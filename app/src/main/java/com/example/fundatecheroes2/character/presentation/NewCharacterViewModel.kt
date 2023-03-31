package com.example.fundatecheroes2.character.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundatecheroes2.App
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun validarCampos(
        nome: String?, url: String?, descricao: String?,
        heroiVilao: String?, idade: String?, aniversario: String?
    ) {
        if (nome == null || url.isNullOrEmpty() || descricao.isNullOrEmpty()
            || heroiVilao.isNullOrEmpty() || idade.isNullOrEmpty() || aniversario.isNullOrEmpty()
        ) {
            state.value = ViewState.ShowErrorNull
            return
        }

        if (!url.contains("@")) {
            state.value = ViewState.ShowErrorUrl
            return
        }
        if(heroiVilao.equals("Herói ou Vilão?")){
            state.value = ViewState.ShowErrorHeroiVilao
            return
        }
        //digitar caractere / no android ainda caía aqui
        /*if (!aniversario.contains("/")) {
            state.value = ViewState.ShowErrorDate
        }*/ else {
            val character = Character(nome, url, descricao, heroiVilao, idade, aniversario)
            val characterString = moshi.adapter(Character::class.java)
                .toJson(character)

            salvar(characterString)
            state.value = ViewState.ShowSuccess
        }
    }

    private fun salvar(characterString: String) {
        val preferences = App.context.getSharedPreferences("bd", AppCompatActivity.MODE_PRIVATE)
        preferences.edit().putString("character", characterString).commit()
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorUrl : ViewState()
    object ShowErrorDate : ViewState()
    object ShowErrorHeroiVilao : ViewState()
    object ShowSuccess : ViewState()
}