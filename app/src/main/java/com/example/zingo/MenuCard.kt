package com.example.zingo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zingo.model.FoodItem
import com.google.firebase.firestore.FirebaseFirestore

class MenuCard : AppCompatActivity() {
    private lateinit var adapter: MenuCardAdapter
    private val itemList = mutableListOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_card)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val category = intent.getStringExtra("category") ?: ""
        val heading = findViewById<TextView>(R.id.heading)
        heading.text = category

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MenuCardAdapter(itemList)
        recyclerView.adapter = adapter

        val db = FirebaseFirestore.getInstance()
        db.collection("Menu_items")
            .whereEqualTo("category", category)
            .get()
            .addOnCompleteListener {task ->
                if(task.isSuccessful)
                {
                    itemList.clear()
                    for(doc in task.result!!)
                    {
                        val item = doc.toObject(FoodItem::class.java)
                        itemList.add(item)
                    }
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Items fetched: ${itemList.size}", Toast.LENGTH_SHORT).show()
                    Log.d("Firestore", "Fetched ${itemList.size} items")

                }
                else{
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}