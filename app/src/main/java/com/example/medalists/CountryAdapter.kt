package com.example.medalists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class CountryAdapter(private val dataList: List<Country>, private val context: Context): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
        val country = dataList[position]
        holder.bind(country)
    }

    inner class ViewHolder(val v:View): RecyclerView.ViewHolder(v) {
        val country = v.findViewById<TextView>(R.id.country)
        val code = v.findViewById<TextView>(R.id.code)
        val medals = v.findViewById<TextView>(R.id.medals)
        val cardView = v.findViewById<CardView>(R.id.card)

        fun bind(item: Country) {
            country.text = item.country
            code.text = item.code
            val totalMedals = item.gold.toInt() + item.silver.toInt() + item.bronze.toInt()
            medals.text = totalMedals.toString()
            if (totalMedals > 100) {
                cardView.setBackgroundColor(ContextCompat.getColor(v.context, R.color.light_blue))
            } else {
                cardView.setBackgroundColor(ContextCompat.getColor(v.context, R.color.purple))
            }

            v.setOnClickListener {
                val message = "Gold: ${item.gold}, Silver: ${item.silver}, Bronze: ${item.bronze}"

                val sharedPref = context.getSharedPreferences("saved", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putString("country", country.text.toString())
                    putString("code", code.text.toString())
                    apply()
                }

                showSnackbar(v, message)
            }
        }
        private fun showSnackbar(view: View, message: String) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}