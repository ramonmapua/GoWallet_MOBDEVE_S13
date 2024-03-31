package com.mobdeve.s13.gowallet

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Date
import java.util.Locale
import android.net.Uri

class AddExpense : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var addReceipt : Button
    lateinit var pinLocation : Button
    lateinit var addExpenseFinish : Button
    lateinit var etDate: EditText
    lateinit var etDescription: EditText
    lateinit var etPrice: EditText
    lateinit var spinnerCategory: Spinner

    private lateinit var db: FirebaseFirestore

    // Camera Permissions
    private val CAMERA_PERMISSION_REQUEST_CODE = 100 // request code for camera permission
    private val CAPTURE_PHOTO_REQUEST_CODE = 101 // request code for capturing phot
    private var photoFilePath: String? = null // file path for saving capture photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        addReceipt = findViewById(R.id.addReceipt)
        pinLocation = findViewById(R.id.pinLocation)
        addExpenseFinish = findViewById(R.id.addExpenseFinish)
        etDate = findViewById(R.id.etDate)
        etDescription = findViewById(R.id.etDescription)
        etPrice = findViewById(R.id.etPrice)
        spinnerCategory = findViewById(R.id.spinnerCategory)

        val categories = arrayOf("Product", "Transportation", "Food")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        etDate.setOnClickListener {
            showDatePicker()
        }

        addReceipt.setOnClickListener {
            // TODO: open camera
            checkCameraPermissionAndOpenCamera()
        }
        pinLocation.setOnClickListener {
            val toPinLocation = Intent(this, PinLocation::class.java)
            startActivity(toPinLocation)
        }
        addExpenseFinish.setOnClickListener {
            // TODO: save data
            saveExpenseData(photoFilePath)
        }
    }

    // For the date
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
    
    // Camera
    private fun checkCameraPermissionAndOpenCamera() {
        // check camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // request camera permission if not granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            openCamera() // open camera if permission granted
        }
    }

    private fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (captureIntent.resolveActivity(packageManager) != null) {
            val photoFile: File? = createPhotoFile()
            if (photoFile != null) {
                val photoUri: Uri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", photoFile)
                photoFilePath = photoFile.absolutePath
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(captureIntent, CAPTURE_PHOTO_REQUEST_CODE)
            }
        }
    }

    private fun createPhotoFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return  File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAPTURE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            saveExpenseData(photoFilePath) // photo capture successfully
        }
    }

    private fun padZero(value: Int): String {
        return if (value < 10) "0$value" else "$value"
    }

    private fun saveExpenseData(photoFilePath: String?) {
        val category = spinnerCategory.selectedItem.toString()
        val description = etDescription.text.toString()
        val price = etPrice.text.toString()
        val date = etDate.text.toString()

        val expenseData = hashMapOf(
            "category" to category,
            "description" to description,
            "price" to price,
            "date" to date,
            "receipt" to photoFilePath
        )

        // Add expense data to Firebase
        db.collection("expenses")
            .add(expenseData)
            .addOnSuccessListener { documentReference ->
                val intent = Intent(this@AddExpense, ViewCountry::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }
}