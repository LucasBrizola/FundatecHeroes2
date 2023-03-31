package com.example.fundatecheroes.login.view.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validarEmailESenha(email: String?, password: String?) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            state.value = ViewState.ShowErrorNull
        }

        if (!email!!.contains("@")) {
            state.value = ViewState.ShowErrorEmail
        } else
            state.value = ViewState.ShowSuccess
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorEmail : ViewState()
    object ShowSuccess : ViewState()
}