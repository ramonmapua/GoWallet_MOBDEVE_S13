package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class ViewCountry : AppCompatActivity() {

    lateinit var addExpense : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        addExpense = findViewById(R.id.addExpenseButton)
        addExpense.setOnClickListener {
            val toAddExpense = Intent(this, AddExpense::class.java)
            startActivity(toAddExpense)
        }
    }
}