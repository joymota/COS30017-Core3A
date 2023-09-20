package com.example.medalists

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.options_layout)

        // Retrieve SharedPreferences data
        val sharedPref = getSharedPreferences("saved", Context.MODE_PRIVATE)
        val savedCountry = sharedPref.getString("country", "")
        val savedCode = sharedPref.getString("code", "")

        // Display the data in TextViews or any other UI elements in your layout
        val countryTextView = findViewById<TextView>(R.id.country)
        val codeTextView = findViewById<TextView>(R.id.code)

        countryTextView.text = "Country: $savedCountry"
        codeTextView.text = "Code: $savedCode"



    }
}