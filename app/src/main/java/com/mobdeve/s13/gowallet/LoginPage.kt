package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class LoginPage : AppCompatActivity() {

    lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val toHomePage = Intent(this, HomePage::class.java)
            startActivity(toHomePage)
        }
    }
}