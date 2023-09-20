package com.example.medalists

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medalists.R.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val list = mutableListOf<Country>()
        val recyclerView: RecyclerView = findViewById(id.listOfCountries)

        val options = findViewById<Button>(R.id.options)
        options.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }

        resources.openRawResource(raw.medallists).bufferedReader()
            .forEachLine {
                val temp = it.split(",")
                list.add(Country(temp[0], temp[1], temp[2], temp[3],
                    temp[4], temp[5]))
        }

        list.forEach {
            Log.i("FILELINE", "${it.country} -- ${it.competed}")

        }


        val adapter = CountryAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }
}