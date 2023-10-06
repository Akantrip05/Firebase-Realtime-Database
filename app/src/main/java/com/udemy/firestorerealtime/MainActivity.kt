package com.udemy.firestorerealtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var btnInsert:Button
    private lateinit var btnFetch:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
        btnInsert= findViewById(R.id.btninsert)
        btnFetch = findViewById(R.id.btnFetch)
        val intent :Intent

        btnInsert.setOnClickListener(){
            startActivity(Intent(this,InsertActivity::class.java))
        }
        btnFetch.setOnClickListener(){
            startActivity(Intent(this,FetchingActivity::class.java))
        }

    }
}