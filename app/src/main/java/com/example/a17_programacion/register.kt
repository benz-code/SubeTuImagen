package com.example.a17_programacion

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var Paso: EditText
    private lateinit var corn: EditText
    private lateinit var continuar: ImageButton
    lateinit var before: ImageButton


    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.paso)
        Paso = findViewById(R.id.contra)
        corn = findViewById(R.id.confirma)
        before = findViewById(R.id.guardar)
        continuar = findViewById(R.id.after)


        auth = Firebase.auth

        before.setOnClickListener {
            register()
        }

        continuar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
    private fun register(){
        val emaile=email.text.toString()
        val contrase=Paso.text.toString()
        val confirmacion=corn.text.toString()
        if(emaile.isBlank()||contrase.isBlank()||confirmacion.isBlank()){

            Toast.makeText(this, "por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        if(contrase!=confirmacion){
            Toast.makeText(this, "confirma que las contraseñas sean iguales", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(emaile, contrase)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "¡Usuario Registrado Correctamente!",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {

                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Fallo de Autenticacion",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}