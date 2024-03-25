/* Aniversario, Manzano, Mapua, S13 */
/* GoWallet */

package com.mobdeve.s13.gowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    lateinit var toGetStartedButton: Button
    lateinit var toLoginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toGetStartedButton = findViewById(R.id.toGetStarted)
        toLoginButton = findViewById(R.id.toLogin)

        toLoginButton.setOnClickListener {
            val toLogin = Intent(this, LoginPage::class.java)
            startActivity(toLogin)
        }
        toGetStartedButton.setOnClickListener {
            val toGetStarted = Intent(this, Register::class.java)
            startActivity(toGetStarted)
        }
    }
}