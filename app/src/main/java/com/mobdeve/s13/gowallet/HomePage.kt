package com.mobdeve.s13.gowallet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class HomePage : AppCompatActivity() {

    lateinit var viewAll : Button
    lateinit var country1 : Button
    lateinit var country2 : Button
    lateinit var country3 : Button
    lateinit var addExpense : Button
    lateinit var currencyConverter : Button
    private lateinit var auth: FirebaseAuth
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Getting active user
        auth = Firebase.auth // Assuming you have initialized Firebase Auth
        val currentUser = auth.currentUser?.email
        val greetingTextView = findViewById<TextView>(R.id.greeting)
        greetingTextView.text = "Hi $currentUser!"

        val currentCountryTextView = findViewById<TextView>(R.id.currentCountry)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewAll = findViewById(R.id.viewAll)
        country1 = findViewById(R.id.country1)
        country2 = findViewById(R.id.country2)
        country3 = findViewById(R.id.country3)
        addExpense = findViewById(R.id.addExpense)
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

        addExpense.setOnClickListener {
            val toAddExpense = Intent(this, AddExpense::class.java)
            startActivity(toAddExpense)
        }
        currencyConverter.setOnClickListener {
            val toCurrencyConverter = Intent(this, CurrencyConverter::class.java)
            startActivity(toCurrencyConverter)
        }
    }
}