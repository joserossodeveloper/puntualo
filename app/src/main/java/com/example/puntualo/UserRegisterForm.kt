package com.example.puntualo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puntualo.models.NewUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class UserRegisterForm: AppCompatActivity() {
    private val db = Firebase.firestore

    private lateinit var editTextUserEmail: EditText
    private lateinit var editTextUserDNI: EditText
    private lateinit var editTextUserName: EditText
    private lateinit var editTextUserFatherLastName: EditText
    private lateinit var editTextUserMotherLastName: EditText
    private lateinit var editTextUserBirthdate: EditText
    private lateinit var editTextUserPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register_form)

        editTextUserEmail = findViewById<EditText>(R.id.editTextUserRegisterEmail)
        editTextUserDNI = findViewById<EditText>(R.id.editTextUserRegisterDNI)
        editTextUserName = findViewById<EditText>(R.id.editTextUserRegisterName)
        editTextUserFatherLastName = findViewById<EditText>(R.id.editTextUserRegisterFatherLastName)
        editTextUserMotherLastName = findViewById<EditText>(R.id.editTextUserRegisterMotherLastName)
        editTextUserBirthdate = findViewById<EditText>(R.id.editTextUserRegisterBirthdate)
        editTextUserPassword = findViewById<EditText>(R.id.editTextUserRegisterPassword)


        val buttonRegisterUser = findViewById<Button>(R.id.buttonFormEditUserRegister)
        buttonRegisterUser.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        val newBirthday = SimpleDateFormat("dd/MM/yyyy").parse(editTextUserBirthdate.text.toString())
        val newDNI = editTextUserDNI.text.toString()
        val newEmail = editTextUserEmail.text.toString()
        val newFatherLastName = editTextUserFatherLastName.text.toString()
        val newMotherLastName = editTextUserMotherLastName.text.toString()
        val newName = editTextUserName.text.toString()
        val newPassword = editTextUserPassword.text.toString()
        val newType = "customer"

        val userData = NewUser(
            newBirthday,
            newDNI,
            newEmail,
            newFatherLastName,
            newMotherLastName,
            newName,
            newPassword,
            newType,
        )

        db.collection("users").add(userData)

        if (true) {
            Toast.makeText(this, "El usuario se registro exitosamente", Toast.LENGTH_LONG).show()
            goToLogin()
        } else {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_LONG).show()
        }

    }

    fun goToLogin () {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}
