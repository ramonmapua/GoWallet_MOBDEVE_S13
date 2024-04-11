package com.mobdeve.s13.gowallet

import Expense
import ExpenseAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ViewCountry : AppCompatActivity() {

    private val db = Firebase.firestore
//    private lateinit var categoryTextView: TextView
//    private lateinit var priceTextView: TextView
//    private lateinit var descriptionTextView: TextView
//    private lateinit var dateTextView: TextView
    private lateinit var country: TextView // Set country name statically (modify if needed)
    private lateinit var rvExpenses: RecyclerView
    private val countryToCheck = "Thailand"
    private val expenses = mutableListOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_country)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        country = findViewById(R.id.country)
        country.text = countryToCheck
//        categoryTextView = findViewById(R.id.expenseCategory)
//        priceTextView = findViewById(R.id.expensePrice)
//        descriptionTextView = findViewById(R.id.expenseDescription)
//        dateTextView = findViewById(R.id.expenseDate)

        // Modify collection name and field names to match your structure
        db.collection("gowallet")
            .get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    if (document.contains("country") && document["country"] == countryToCheck) {   // Found a matching document, set data to TextViews
                        val expense = Expense(
                            document["category"].toString(),
                            document["price"].toString(),
                            document["description"].toString(),
                            document["date"].toString()
                        )
                        expenses.add(expense)
                        println("Document : $expense")
                    }
                }
            rvExpenses = findViewById(R.id.recyclerView) // Replace 'rvExpenses' with the ID of your RecyclerView
            val layoutManager = LinearLayoutManager(this)
            val adapter = ExpenseAdapter(expenses)
            rvExpenses.layoutManager = layoutManager
            rvExpenses.adapter = adapter
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}