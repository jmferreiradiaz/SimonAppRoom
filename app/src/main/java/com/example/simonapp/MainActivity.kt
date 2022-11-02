package com.example.simonapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private var listaNumeros = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener{turnoIA()}
    }

    fun turnoIA(){
        val numAle = Random(System.nanoTime()).nextInt(4)
        Log.d("Numero Aleatorio", ""+numAle)
        listaNumeros.add(numAle)
        for (i in 0..listaNumeros.size - 1){
            //Encendemos los colores
            encenderColores(listaNumeros[i])
            //Apagar colores
            apagarColores(listaNumeros[i])
        }
    }

    fun turnoJugador(){

    }

    fun encenderColores (numAle: Int){
        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)
        when (numAle) {
            0 -> btnRojo.backgroundTintList = getColorStateList(R.color.rojoClaro)
            1 -> btnVerde.backgroundTintList = getColorStateList(R.color.verdeClaro)
            2 -> btnAmarillo.backgroundTintList = getColorStateList(R.color.amarilloClaro)
            3 -> btnAzul.backgroundTintList = getColorStateList(R.color.azulClaro)
        }
    }

    fun apagarColores (numAle: Int){
        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)
        when (numAle) {
            0 -> btnRojo.backgroundTintList = getColorStateList(R.color.rojo)
            1 -> btnVerde.backgroundTintList = getColorStateList(R.color.verde)
            2 -> btnAmarillo.backgroundTintList = getColorStateList(R.color.amarillo)
            3 -> btnAzul.backgroundTintList = getColorStateList(R.color.azul)
        }
    }
}