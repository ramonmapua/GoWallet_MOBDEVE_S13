package com.mobdeve.s13.gowallet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.w3c.dom.Text

class HomePage : AppCompatActivity() {

    lateinit var viewAll : Button
    lateinit var country1 : Button
    lateinit var country2 : Button
    lateinit var country3 : Button
    lateinit var addCountry : Button
    lateinit var currencyConverter : Button
    private lateinit var auth: FirebaseAuth
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_page)

        // Getting active user
        auth = Firebase.auth // Assuming you have initialized Firebase Auth
        val currentUser = auth.currentUser?.email
        val greetingTextView = findViewById<TextView>(R.id.greeting) // Replace with actual id
        greetingTextView.text = "Hi $currentUser!"

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewAll = findViewById(R.id.viewAll) // TODO: add logic
        country1 = findViewById(R.id.country1) // TODO: add logic
        country2 = findViewById(R.id.country2) // TODO: add logic
        country3 = findViewById(R.id.country3) // TODO: add logic
        addCountry = findViewById(R.id.addCountry)
        currencyConverter = findViewById(R.id.currencyConverter)

        // TODO: change ViewCountry depending on the data provided
        country1.setOnClickListener {
            val toCountry1 = Intent(this, ViewCountry::class.java)
            startActivity(toCountry1)
        }

        // TODO: remove dummy function
        country2.setOnClickListener {
            val toCountry1 = Intent(this, ViewCountry::class.java)
            startActivity(toCountry1)
        }

        // TODO: remove dummy function
        country3.setOnClickListener {
            val toCountry1 = Intent(this, ViewCountry::class.java)
            startActivity(toCountry1)
        }

        addCountry.setOnClickListener {
            val toAddCountry = Intent(this, AddCountry::class.java)
            startActivity(toAddCountry)
        }
        currencyConverter.setOnClickListener {
            val toCurrencyConverter = Intent(this, CurrencyConverter::class.java)
            startActivity(toCurrencyConverter)
        }
    }
}