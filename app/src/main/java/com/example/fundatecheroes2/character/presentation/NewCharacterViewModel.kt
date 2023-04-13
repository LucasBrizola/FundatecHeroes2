package com.example.fundatecheroes2.character.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundatecheroes2.App
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state
    private var listCharacter: MutableList<Character>? = mutableListOf()
    val preferences = App.context.getSharedPreferences("bd", AppCompatActivity.MODE_PRIVATE)

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
        if (heroiVilao.equals("Herói ou Vilão?")) {
            state.value = ViewState.ShowErrorHeroiVilao
            return
        }
        //digitar caractere / no android ainda caía aqui
        /*if (!aniversario.contains("/")) {
            state.value = ViewState.ShowErrorDate
        }*/
        else {
            val character = Character(nome, url, descricao, heroiVilao, idade, aniversario)
            listCharacter = puxarLista(preferences)
            listCharacter?.add(character)
            val listString = moshi.adapter(MutableList::class.java)
                .toJson(listCharacter)

            salvar(preferences, listString)
            state.value = ViewState.ShowSuccess
        }
    }
    private fun puxarLista(preferences: SharedPreferences): MutableList<Character>? {
            val characterListString = preferences.getString("characterList", "")
            if(characterListString.isNullOrEmpty()){
                return mutableListOf()
            }
        else {
                val listType =
                    Types.newParameterizedType(MutableList::class.java, Character::class.java)
                val adapter: JsonAdapter<MutableList<Character>> = moshi.adapter(listType)
                return adapter.fromJson(characterListString)!!
            }
    }

    private fun salvar(preferences: SharedPreferences, listString: String) {
        preferences.edit().putString("characterList", listString).commit()
    }
}

    sealed class ViewState {
        object ShowErrorNull : ViewState()
        object ShowErrorUrl : ViewState()
        object ShowErrorDate : ViewState()
        object ShowErrorHeroiVilao : ViewState()
        object ShowSuccess : ViewState()
    }