package com.example.simonapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private var listaNumerosIA = arrayListOf<Int>()
    private var listaNumerosJugador = arrayListOf<Int>()
    private var cont = 0;
    private var derrota = true;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val btnStart = findViewById<Button>(R.id.btnStart)
        //Cuando se pulse el botón Start, el juego comienza
        btnStart.setOnClickListener{turnoIA()}

        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)

            btnAmarillo.setOnClickListener { turnoJugador(btnAmarillo) }
            btnRojo.setOnClickListener { turnoJugador(btnRojo) }
            btnVerde.setOnClickListener { turnoJugador(btnVerde) }
            btnAzul.setOnClickListener { turnoJugador(btnAzul) }


    }

    fun turnoIA(){
        Log.d("Derrota", ""+derrota)
        val numAle = Random(System.nanoTime()).nextInt(4)
        Log.d("Numero Aleatorio", ""+numAle)
        listaNumerosIA.add(numAle)
        GlobalScope.launch {
            for (i in 0..listaNumerosIA.size - 1){
                //Encendemos los colores
                delay(600)
                encenderColores(listaNumerosIA[i])
                delay(500)
                //Apagar colores
                apagarColores(listaNumerosIA[i])
            }
        }
        derrota = false
    }

    fun turnoJugador(btn:View){

        when(btn.id){
            R.id.btnRojo -> listaNumerosJugador.add(0);
            R.id.btnVerde -> listaNumerosJugador.add(1);
            R.id.btnAmarillo -> listaNumerosJugador.add(2);
            R.id.btnAzul -> listaNumerosJugador.add(3);
        }

        if(listaNumerosJugador[cont] == listaNumerosIA[cont]){
            cont++
        } else {
            Log.d("Derrota", "Has perdido")
            derrota = true
        }

        if ((listaNumerosJugador.size == listaNumerosIA.size) && !derrota){
            Log.d("Derrota", "Entra")
            listaNumerosJugador = arrayListOf<Int>()
            turnoIA()
        }
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