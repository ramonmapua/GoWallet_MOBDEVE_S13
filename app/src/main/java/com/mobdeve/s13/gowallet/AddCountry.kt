package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore

class AddCountry : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerCountry = findViewById<Spinner>(R.id.spinnerCountry)
        val spinnerCurrency = findViewById<Spinner>(R.id.spinnerCurrency)
        val spinnerConvert = findViewById<Spinner>(R.id.spinnerConvert)
        spinnerCountry.adapter = adapter
        spinnerCurrency.adapter = adapter
        spinnerConvert.adapter = adapter

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val selectedCountry = spinnerCountry.selectedItem.toString()
            val selectedCurrency = spinnerCurrency.selectedItem.toString()
            val selectedConvertTo = spinnerConvert.selectedItem.toString()

            // Save data to Firestore
            saveCountry(selectedCountry, selectedCurrency, selectedConvertTo)
        }
    }
    // TODO: add logic

    private fun saveCountry(country: String, currency: String, convertTo: String) {
        val countryData = hashMapOf(
            "name" to country,
            "currency" to currency,
            "convertTo" to convertTo
        )

        db.collection("countries")
            .add(countryData)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")

                val intent = Intent(this, AddExpense::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }
}