package com.example.zingo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Opening_Quotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_opening_quotes)
        val slide1 = findViewById<LinearLayout>(R.id.linearLayout)
        val slide2 = findViewById<LinearLayout>(R.id.linearLayout2)
        val slide3 = findViewById<LinearLayout>(R.id.linearLayout3)
        val slide4 = findViewById<LinearLayout>(R.id.linearLayout4)
        val btn = findViewById<Button>(R.id.getStarted)
        var quoteEnd = false

        slide1.visibility = View.VISIBLE
        slide2.visibility = GONE
        slide3.visibility = GONE
        slide4.visibility = GONE

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            slide1.visibility = GONE
            slide2.visibility = View.VISIBLE
        }, 2000)

        handler.postDelayed({
            slide2.visibility = GONE
            slide3.visibility = View.VISIBLE
        }, 4000)

        handler.postDelayed({
            slide3.visibility = GONE
            slide4.visibility = View.VISIBLE
        }, 6000)

        handler.postDelayed({
            if(!quoteEnd)
            {
                quoteEnd = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 8000)

        btn.setOnClickListener{
            quoteEnd = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}