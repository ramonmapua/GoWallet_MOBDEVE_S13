package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class AddExpense : AppCompatActivity() {

    lateinit var addReceipt : Button
    lateinit var pinLocation : Button
    lateinit var addExpenseFinish : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        addReceipt = findViewById(R.id.addReceipt)
        pinLocation = findViewById(R.id.pinLocation)
        addExpenseFinish = findViewById(R.id.addExpenseFinish)

        addReceipt.setOnClickListener {
            // TODO: open camera
        }
        pinLocation.setOnClickListener {
            val toPinLocation = Intent(this, PinLocation::class.java)
            startActivity(toPinLocation)
        }
        addExpenseFinish.setOnClickListener {
            // TODO: save data
        }
    }
}