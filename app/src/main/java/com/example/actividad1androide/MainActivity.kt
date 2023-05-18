package com.example.actividad1androide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.TextView
import android.widget.Button
import android.util.Log
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts:TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts= TextToSpeech(this, this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}
    }


    private fun speak(){
        var message: String = findViewById<EditText>(R.id.etMessage).text.toString()

        if (message.isEmpty()){
            findViewById<TextView>(R.id.etMessage).text="Introduzca un texto"
            message= "en serio? Escribe algo en el EditText"
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH,null, "")
    }

    override fun onInit(status: Int){
        if(status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text="hello Kotlin :)"
            tts!!.setLanguage(Locale("US"))
        }
        else{
            findViewById<TextView>(R.id.tvStatus).text="no disponible : ("
        }
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}