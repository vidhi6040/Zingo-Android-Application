package com.example.zingo

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Types.NULL

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_register)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.emailReg)
        val password = findViewById<EditText>(R.id.passwordReg)
        val btn = findViewById<Button>(R.id.Register)
        val loginRedirect = findViewById<TextView>(R.id.loginRedirect)
        btn.setOnClickListener()
        {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val usernameText = username.text.toString()

            if(usernameText.isNotEmpty() && emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            val userMap = hashMapOf(
                                "username" to usernameText,
                                "email" to emailText
                            )
                            if(userId != null)
                            {
                                db.collection("Users").document(userId).set(userMap)
                            }
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else
            {
                Toast.makeText(this, "Pls fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        loginRedirect.setOnClickListener()
        {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}