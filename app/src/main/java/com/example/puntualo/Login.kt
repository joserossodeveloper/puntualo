package com.example.puntualo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private val db = Firebase.firestore

    lateinit var textEmail: EditText
    lateinit var textPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textEmail = findViewById<EditText>(R.id.editTextEmail)
        textPassword = findViewById<EditText>(R.id.editTextPassword)

        /*
        Investigar como ejecutar scripts de kotlin por consola
        val loadData = LoadData()
        loadData.loadRestaurants()*/

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            loginUser()
        }

        val buttonSignup = findViewById<Button>(R.id.buttonSignup)
        buttonSignup.setOnClickListener {
            goToUserRegister()
        }
    }

    fun loginUser() {
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()

        if(email.length < 1 || password.length < 1) {
            Toast.makeText(this, "Ingrese correctamente su correo y contraseña", Toast.LENGTH_LONG).show()
            return
        }

        var userSuccess = false

        db.collection("users")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .limit(1)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val timestamp = document.data.getValue("birthday") as com.google.firebase.Timestamp
                    val birthdayDate = timestamp.toDate()

                    val item = User(
                        document.data.getValue("email").toString(),
                        "no disponible",
                        document.data.getValue("type").toString(),
                        birthdayDate,
                        document.data.getValue("dni").toString(),
                        document.data.getValue("name").toString(),
                        document.data.getValue("father_last_name").toString(),
                        document.data.getValue("mother_last_name").toString(),
                        document.id,
                    )
                    Log.d("loginUser", "${document.id} => ${document.data}")
                    GlobalInfo.user = item
                    userSuccess = true
                }

                if(!userSuccess) {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                } else {
                    goToHome()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error en login. Intente más tarde", Toast.LENGTH_LONG).show()
                Log.w("loginUser", "Error getting documents.", exception)
            }
    }

    fun goToHome () {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToUserRegister() {
        val intent = Intent(this, UserRegisterForm::class.java)
        startActivity(intent)
    }

}