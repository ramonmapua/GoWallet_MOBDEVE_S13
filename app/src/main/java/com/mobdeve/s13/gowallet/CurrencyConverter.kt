package com.mobdeve.s13.gowallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class CurrencyConverter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val currencies = mapOf(
            "USD" to 1.0,
            "PHP" to 56.16,
            "SGD" to 1.35,
            "THB" to 36.47,
            "HKD" to 7.83,
            "JPY" to 151.34
        )

        val amountEntered = findViewById<EditText>(R.id.amountEntered)
        val spinnerFrom = findViewById<Spinner>(R.id.spinnerFrom)
        val spinnerTo = findViewById<Spinner>(R.id.spinnerTo)
        val amountConverted = findViewById<TextView>(R.id.amountConverted)
        val convertButton = findViewById<Button>(R.id.convertButton)

        spinnerFrom.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies.keys.toList())
        spinnerTo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies.keys.toList())

        convertButton.setOnClickListener {
            val amount = amountEntered.text.toString().toDoubleOrNull()
            if (amount == null || amount == 0.0) {
                return@setOnClickListener
            }

            val fromCurrency = spinnerFrom.selectedItem.toString()
            val toCurrency = spinnerTo.selectedItem.toString()

            val exchangeRate = currencies[fromCurrency]!! / currencies[toCurrency]!!

            val result = String.format("%.2f", amount * exchangeRate)
            amountConverted.text = "$result"
        }
    }
}