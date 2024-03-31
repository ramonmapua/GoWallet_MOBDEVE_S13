package com.mobdeve.s13.gowallet

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import java.time.Month
import java.time.Year
import java.util.Calendar

class AddExpense : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var addReceipt : Button
    lateinit var pinLocation : Button
    lateinit var addExpenseFinish : Button
    lateinit var etDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        addReceipt = findViewById(R.id.addReceipt)
        pinLocation = findViewById(R.id.pinLocation)
        addExpenseFinish = findViewById(R.id.addExpenseFinish)
        etDate = findViewById(R.id.etDate)

        etDate.setOnClickListener {
            showDatePicker()
        }

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

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "${padZero(month + 1)}/${padZero(dayOfMonth)}/$year"
        etDate.setText(selectedDate)
    }

    private fun padZero(value: Int): String {
        return if (value < 10) "0$value" else "$value"
    }
}