package com.mobdeve.s13.gowallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ViewCountry : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var categoryTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var country: TextView // Set country name statically (modify if needed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        country = findViewById(R.id.country)
        categoryTextView = findViewById(R.id.expenseCategory)
        priceTextView = findViewById(R.id.expensePrice)
        descriptionTextView = findViewById(R.id.expenseDescription)
        dateTextView = findViewById(R.id.expenseDate)

        loadExpenses()
    }

    private fun loadExpenses() {

        val countryToCheck = "Thailand"

        // Modify collection name and field names to match your structure
        db.collection("gowallet")
            .get()
            .addOnSuccessListener {documents ->
                val tableLayout = findViewById<TableLayout>(R.id.expensesTable)  // Replace with your table layout id
                tableLayout.removeAllViews()  // Clear existing rows before adding new ones

                for (document in documents) {
                    if (document.contains("country") && document["country"] == countryToCheck) {   // Found a matching document, set data to TextViews
                        // Create a new TableRow for each matching document
                        val tableRow = TableRow(this)
                        val categoryTextView = TextView(this)
                        val priceTextView = TextView(this)
                        val descriptionTextView = TextView(this)
                        val dateTextView = TextView(this)

                        country.text = countryToCheck
                        categoryTextView.text = document["category"].toString()
                        priceTextView.text = document["price"].toString()
                        descriptionTextView.text = document["description"].toString()
                        dateTextView.text = document["date"].toString()

                        // Add TextViews to the TableRow
                        tableRow.addView(categoryTextView)
                        tableRow.addView(priceTextView)
                        tableRow.addView(descriptionTextView)
                        tableRow.addView(dateTextView)

                        // Add the TableRow to the TableLayout
                        tableLayout.addView(tableRow)
                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}