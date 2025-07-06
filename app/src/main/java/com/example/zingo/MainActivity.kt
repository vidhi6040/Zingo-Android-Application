package com.example.zingo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val profile = findViewById<ImageView>(R.id.profile)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val userId = auth.currentUser?.uid
        val hello = findViewById<TextView>(R.id.hello)
        val breakfast = findViewById<TextView>(R.id.breakfast)
        val lunch = findViewById<TextView>(R.id.lunch)

        profile.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }

        if(userId != null)
        {
            db.collection("Users").document(userId).get().addOnCompleteListener(this) {task ->
                if(task.isSuccessful)
                {
                    val username = task.result.getString("username")
                    hello.text = ("Hello, " + username) ?: "Hello, User"
                }
                else
                {
                    hello.text = "Hello, User"
                }
            }
        }

        breakfast.setOnClickListener {
            val intent = Intent(this, MenuCard::class.java)
            intent.putExtra("category", "Breakfast")
            startActivity(intent)
        }

        lunch.setOnClickListener {
            val intent = Intent(this, MenuCard::class.java)
            intent.putExtra("category", "Lunch")
            startActivity(intent)
        }
    }
}