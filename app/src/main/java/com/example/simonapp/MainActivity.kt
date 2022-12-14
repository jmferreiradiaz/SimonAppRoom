package com.example.simonapp

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), IComunicador {
    //Lista donde guardaremos la secuencia de la máquina
    private var listaNumerosIA = arrayListOf<Int>()

    //Lista donde guardaremos la secuencia del jugador
    private var listaNumerosJugador = arrayListOf<Int>()

    //contador que guardar las pulsaciones del jugador en cada turno
    private var cont = 0

    //booleano que indica si los botones son clickables para el jugador o no
    private var isBtnClickable = false

    //variable que indica los niveles pasados por el jugador
    private var nivel = 0

    //variables de los botones
    private lateinit var btnAmarillo: View
    private lateinit var btnRojo:View
    private lateinit var btnVerde:View
    private lateinit var btnAzul:View
    private lateinit var btnStart:Button

    //variables para los sonidos
    private lateinit var media1: MediaPlayer
    private lateinit var media2: MediaPlayer
    private lateinit var media3: MediaPlayer
    private lateinit var media4: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        //declaramos los sonidos
        media1 = MediaPlayer.create(this, R.raw.mi)
        media2 = MediaPlayer.create(this, R.raw.mi1)
        media3 = MediaPlayer.create(this, R.raw.re)
        media4 = MediaPlayer.create(this, R.raw.sol)

        //declaramos los botones
        btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        btnRojo = findViewById<View>(R.id.btnRojo)
        btnVerde = findViewById<View>(R.id.btnVerde)
        btnAzul = findViewById<View>(R.id.btnAzul)
        btnStart = findViewById<Button>(R.id.btnStart)
    }

    override fun onResume() {
        super.onResume()
        btnStart.setOnClickListener { turnoIA() }
    }

    /**
     * Método que genera la secuencia de la máquina
     */
    fun turnoIA() {
        //Desactivamos los botones cuando la secuencia empieza
        isBtnClickable = false
        btnStart.isClickable = false

        //Reiniciamos el contador
        cont = 0

        //Generamos un número aleatorio de el 0 al 3
        val numAle = Random(System.nanoTime()).nextInt(4)

        //Guardamos el número aleatorio en la lista de nuestra secuencia
        listaNumerosIA.add(numAle)

        //Lanzamos la corutina de la iluminación de los botones
        GlobalScope.launch {
            for (i in 0..listaNumerosIA.size - 1) {
                //Encendemos los colores
                delay(500)
                encenderColores(listaNumerosIA[i])
                reproducirSonidos(listaNumerosIA[i])
                delay(500)
                //Apagar colores
                apagarColores(listaNumerosIA[i])
            }
            //Volvemos a activar los botones cuando la secuencia acaba
            isBtnClickable = true
        }

    }

    /**
     * Método que se inicia cuando el jugador pulsa una de las 4 vistas.
     */
    override fun onPulsarBtnJugador(btn: View) {
        //Variable donde guardaremos el número correspondiente a cada botón
        var numColor = 0

        //Booleano que indica si el jugador ha sido derrotado o no
        var derrota = false;

        //Se asignan los números a los botones
        //Si el botón se puede pulsar entra
        if (isBtnClickable) {
            when (btn.id) {
                R.id.btnRojo -> numColor = 0;
                R.id.btnVerde -> numColor = 1;
                R.id.btnAmarillo -> numColor = 2;
                R.id.btnAzul -> numColor = 3;
            }

            //Se añade el número a la lista de la secuencia del jugador
            listaNumerosJugador.add(numColor)

            GlobalScope.launch {

                //Encendemos los colores
                encenderColores(numColor)
                reproducirSonidos(numColor)
                delay(500)
                //Apagar colores
                apagarColores(numColor)
            }


            //Sí la secuencia de la ia es igual a la del jugador sigue el juego
            if (numColor == listaNumerosIA[cont]) {
                cont++
            }
            //Si no se acaba el juego y se reinicia
            else {
                //Se borran las 2 listas para reiniciar el juego
                listaNumerosIA.clear()
                listaNumerosJugador.clear()
                derrota = true
                nivel = 0;
                findViewById<TextView>(R.id.lblNivel).text = "Nivel: " + nivel
                isBtnClickable = false
                btnStart.isClickable = true
            }

            //Si el tamaño de las 2 listas son iguales y derrota está a false
            if ((listaNumerosJugador.size == listaNumerosIA.size) && !derrota) {
                //Se borrá la lista del jugador
                listaNumerosJugador.clear()
                isBtnClickable = false
                nivel++;
                findViewById<TextView>(R.id.lblNivel).text = "Nivel: " + nivel
                //Le toca a las IA
                GlobalScope.launch {
                    delay(600)
                    turnoIA()
                }

            }
        }
    }


    /**
     * Cambia el color del botón actual por otro de tono más claro
     */
    fun encenderColores(num: Int) {
        when (num) {
            0 -> btnRojo.backgroundTintList = getColorStateList(R.color.rojoClaro)
            1 -> btnVerde.backgroundTintList = getColorStateList(R.color.verdeClaro)
            2 -> btnAmarillo.backgroundTintList = getColorStateList(R.color.amarilloClaro)
            3 -> btnAzul.backgroundTintList = getColorStateList(R.color.azulClaro)
        }
    }

    /**
     * Cambia el color del botón actual por otro de tono más oscuro
     */
    fun apagarColores(num: Int) {
        val btnAmarillo = findViewById<View>(R.id.btnAmarillo)
        val btnRojo = findViewById<View>(R.id.btnRojo)
        val btnVerde = findViewById<View>(R.id.btnVerde)
        val btnAzul = findViewById<View>(R.id.btnAzul)
        when (num) {
            0 -> btnRojo.backgroundTintList = getColorStateList(R.color.rojo)
            1 -> btnVerde.backgroundTintList = getColorStateList(R.color.verde)
            2 -> btnAmarillo.backgroundTintList = getColorStateList(R.color.amarillo)
            3 -> btnAzul.backgroundTintList = getColorStateList(R.color.azul)
        }
    }

    /**
     * Reproduce los sonidos al pulsar los botones
     */
    fun reproducirSonidos(num: Int) {
        //Comprobamos si están sonando
        if (media1.isPlaying) {
            media1.pause()
            media1.seekTo(0)
        }

        if (media2.isPlaying) {
            media2.pause()
            media2.seekTo(0)
        }

        if (media3.isPlaying) {
            media3.pause()
            media3.seekTo(0)
        }

        if (media4.isPlaying) {
            media4.pause()
            media4.seekTo(0)
        }

        when (num) {
            0 -> {
                media1.setVolume(100F, 100F)
                if (!media1.isPlaying) {
                    media1.start()
                }
            }
            1 -> {
                media2.setVolume(100F, 100F)
                if (!media2.isPlaying) {
                    media2.start()
                }
            }
            2 -> {
                media3.setVolume(100F, 100F)
                if (!media3.isPlaying) {
                    media3.start()
                }
            }
            3 -> {
                media4.setVolume(100F, 100F)
                if (!media4.isPlaying) {
                    media4.start()
                }
            }
        }
    }
}