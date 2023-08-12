package com.curso.android.app.practica.proyectofinal.model

class Comparador(var valor: String) {

    public fun evaluar(texto1: String, texto2: String) {
        var resultado = texto1 == texto2

        if (resultado) {
            this.valor = "iguales"
        } else {
            this.valor = "distintos"
        }
    }
}