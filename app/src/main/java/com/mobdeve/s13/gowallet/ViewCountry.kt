package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore

class ViewCountry : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        db = FirebaseFirestore.getInstance()
        tableLayout = findViewById(R.id.expenseTable)

        loadExpenses()
    }

    private fun loadExpenses() {
        val countryName = intent.getStringExtra("countryName") ?: return

        db.collection("expenses")
            .whereEqualTo("country", countryName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val category = document.getString("category") ?: ""
                    val price = document.getString("price") ?: ""
                    val description = document.getString("description") ?: ""
                    val date = document.getString("date") ?: ""

                    addExpenseRow(category, price, description, date)
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    private fun addExpenseRow(category: String, price: String, description: String, date: String) {
        val row = TableRow(this)

        val categoryTextView = TextView(this).apply {
            text = category
        }

        val priceTextView = TextView(this).apply {
            text = price
        }

        val descriptionTextView = TextView(this).apply {
            text = description
        }

        val dateTextView = TextView(this).apply {
            text = date
        }

        row.addView(categoryTextView)
        row.addView(priceTextView)
        row.addView(descriptionTextView)
        row.addView(dateTextView)

        tableLayout.addView(row)
    }
}