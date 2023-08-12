package com.curso.android.app.practica.proyectofinal.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.practica.proyectofinal.model.Comparador
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val comparador: LiveData<Comparador> get() = _comparador
    private var _comparador = MutableLiveData<Comparador>(Comparador(""))

    fun comparar(texto1: String, texto2: String) {
        viewModelScope.launch {
            var comp = Comparador("")
            comp.evaluar(texto1,texto2)
            _comparador.value = comp
        }


    }
}
