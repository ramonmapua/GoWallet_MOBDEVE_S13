package com.mobdeve.s13.gowallet

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Toolbar setup (if needed)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Get references to UI elements
        val firstName = findViewById<EditText>(R.id.getFirstName)
        val lastName = findViewById<EditText>(R.id.getLastName)
        val username = findViewById<EditText>(R.id.getUsername)
        val password = findViewById<EditText>(R.id.getPassword)
        val email = findViewById<EditText>(R.id.getEmail)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Register button click listener
        registerButton.setOnClickListener {
            val enteredEmail = email.text.toString()
            val enteredPassword = password.text.toString()

            // Validate email and password
            if (TextUtils.isEmpty(enteredEmail)) {
                email.error = "Email is required."
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(enteredPassword)) {
                password.error = "Password is required."
                return@setOnClickListener
            }

            // Call createAccount function for registration
            createAccount(enteredEmail, enteredPassword)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Handle UI updates based on user object (signed in/out)
        // You can navigate to another activity, display success messages, etc.
        if (user != null) {
            // User is signed in, redirect to Home Activity
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish() // Optional: Close registration activity after successful registration
            Log.d(TAG, "updateUI:success")
        } else {
            // User is signed out
            Log.d(TAG, "updateUI:failure")
        }
    }

    companion object {
        private const val TAG = "Register"
    }
}
