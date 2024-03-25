package com.mobdeve.s13.gowallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar

class AddCountry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
    }
    // TODO: add logic
}