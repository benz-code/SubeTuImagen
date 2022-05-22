package com.example.a17_programacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var tarde: ImageButton
    lateinit var correos: EditText
    private lateinit var contrasena: EditText
    lateinit var nexto: ImageButton

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tarde = findViewById(R.id.before)
        nexto = findViewById(R.id.next)
        correos = findViewById(R.id.correo)
        contrasena = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()


        nexto.setOnClickListener {
            inicio()
        }

        tarde.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun inicio(){

        val email=correos.text.toString()
        val contras=contrasena.text.toString()

        auth.signInWithEmailAndPassword(email,contras).addOnCompleteListener(this){

            if(it.isSuccessful){
                Toast.makeText(this, "Acceso Autorizado!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, imagen::class.java)
                startActivity(intent)
                finish()
            }else

                Toast.makeText(this, "Acceso no autorizado! ", Toast.LENGTH_SHORT).show()
        }

    }


}