package com.example.puntualo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.User
import com.example.puntualo.models.UserEdit
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class UserForm : AppCompatActivity() {

    private val db = Firebase.firestore
    private var user: User? = null

    private lateinit var editTextUserEmail: EditText
    private lateinit var editTextUserDNI: EditText
    private lateinit var editTextUserName: EditText
    private lateinit var editTextUserFatherLastName: EditText
    private lateinit var editTextUserMotherLastName: EditText
    private lateinit var editTextUserBirthdate: EditText

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        editTextUserEmail = findViewById<EditText>(R.id.editTextUserEmail)
        editTextUserDNI = findViewById<EditText>(R.id.editTextUserDNI)
        editTextUserName = findViewById<EditText>(R.id.editTextUserName)
        editTextUserFatherLastName = findViewById<EditText>(R.id.editTextUserFatherLastName)
        editTextUserMotherLastName = findViewById<EditText>(R.id.editTextUserMotherLastName)
        editTextUserBirthdate = findViewById<EditText>(R.id.editTextUserBirthdate)


        user = GlobalInfo.user
        user?.let { showUserData(it) }

        val buttonEditUser = findViewById<Button>(R.id.buttonFormEditUser)
        buttonEditUser.setOnClickListener {
            editUser()
        }

    }


    private fun showUserData(user: User) {
        editTextUserEmail.setText(user.email)
        editTextUserDNI.setText(user.dni)
        editTextUserName.setText(user.name)
        editTextUserFatherLastName.setText(user.father_last_name)
        editTextUserMotherLastName.setText(user.mother_last_name)

        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(user.birthday)
        editTextUserBirthdate.setText(date)
    }

    private fun editUser(){
        val newName = editTextUserName.text.toString()
        val newFatherLastName = editTextUserFatherLastName.text.toString()
        val newMotherLastName = editTextUserMotherLastName.text.toString()

        val userData = UserEdit(
            newName,
            newFatherLastName,
            newMotherLastName
        )

        db.collection("users").document(user?.id!!)
            .set(userData, SetOptions.merge())
            .addOnSuccessListener {
                GlobalInfo.user?.name  = newName
                GlobalInfo.user?.father_last_name = newFatherLastName
                GlobalInfo.user?.mother_last_name = newMotherLastName
                finish()
                Log.d("editUser", "editUser successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("editUser", "Error writing document", e)
                Toast.makeText(this, "Error al editer su perfil", Toast.LENGTH_LONG).show()
            }
    }
}