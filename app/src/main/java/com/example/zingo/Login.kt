package com.example.zingo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val auth = git .getInstance()
        setContentView(R.layout.activity_login)
        val email = findViewById<EditText>(R.id.emailLog)
        val password = findViewById<EditText>(R.id.passwordLog)
        val btn = findViewById<Button>(R.id.Login)
        val registerRedirect = findViewById<TextView>(R.id.registerRedirect)
        btn.setOnClickListener()
        {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            if(emailText.isNotEmpty() && passwordText.isNotEmpty())
            {
                auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this) {task ->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Pls fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        registerRedirect.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}