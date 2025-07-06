package com.example.zingo

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        val loginOpt = findViewById<TextView>(R.id.loginOpt)
        val registerOtp = findViewById<TextView>(R.id.registerOtp)
        val logoutOpt = findViewById<TextView>(R.id.logoutOpt)

        val userText = findViewById<TextView>(R.id.user)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val userId = auth.currentUser?.uid

        loginOpt.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        registerOtp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

        if(userId != null)
        {
            db.collection("Users").document(userId).get().addOnCompleteListener(this) {task ->
                if(task.isSuccessful)
                {
                    val username = task.result.getString("username")
                    userText.text = username ?: "User"
                    loginOpt.visibility = View.GONE
                    registerOtp.visibility = View.GONE
                    logoutOpt.visibility = View.VISIBLE
                }
                else
                {
                    userText.text = "User"
                }
            }
        }

        logoutOpt.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, Profile::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val FAQCon = findViewById<TextView>(R.id.FAQCon)
        val ABOUTCon = findViewById<TextView>(R.id.ABOUTCon)
        val TERMSCon = findViewById<TextView>(R.id.TERMSCon)
        val POLICYCon = findViewById<TextView>(R.id.POLICYCon)
        val MORECon = findViewById<TextView>(R.id.MORECon)

        val FAQ = findViewById<TextView>(R.id.FAQ)
        val ABOUT = findViewById<TextView>(R.id.ABOUT)
        val TERMS = findViewById<TextView>(R.id.TERMS)
        val POLICY = findViewById<TextView>(R.id.POLICY)
        val MORE = findViewById<TextView>(R.id.MORE)

        FAQCon.setOnClickListener {
            FAQ.visibility = if (FAQ.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        ABOUTCon.setOnClickListener {
            ABOUT.visibility = if (ABOUT.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        TERMSCon.setOnClickListener {
            TERMS.visibility = if (TERMS.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        POLICYCon.setOnClickListener {
            POLICY.visibility = if (POLICY.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        MORECon.setOnClickListener {
            MORE.visibility = if (MORE.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}