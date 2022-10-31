package com.example.simonapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private var cont = 0;

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
        for (i in 0..cont){
            val numAle = (0..3).random()
            //Encendemos los colores
            encenderColores(numAle)
            Thread.sleep(1_000)
            //Apagar colores
            apagarColores(numAle)
        }


    }

    fun encenderColores (numAle: Int){
        Log.d("Numero Aleatorio", ""+numAle)
        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)
        when (numAle) {
            0 -> btnRojo.background.setTint(resources.getColor(R.color.rojoClaro))
            1 -> btnVerde.background.setTint(resources.getColor(R.color.verdeClaro))
            2 -> btnAmarillo.background.setTint(resources.getColor(R.color.amarilloClaro))
            3 -> btnAzul.background.setTint(resources.getColor(R.color.azulClaro))
        }
    }

    fun apagarColores (numAle: Int){
        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)
        when (numAle) {
            0 -> btnRojo.background.setTint(resources.getColor(R.color.rojo))
            1 -> btnVerde.background.setTint(resources.getColor(R.color.verde))
            2 -> btnAmarillo.background.setTint(resources.getColor(R.color.amarillo))
            3 -> btnAzul.background.setTint(resources.getColor(R.color.azul))
        }
    }
}