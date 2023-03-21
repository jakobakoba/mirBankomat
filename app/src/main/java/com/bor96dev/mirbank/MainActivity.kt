package com.bor96dev.mirbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ExchangeRateViewModel
    private lateinit var ratesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ratesTextView = findViewById(R.id.rates)

        viewModel = ViewModelProvider(this).get(ExchangeRateViewModel::class.java)
        viewModel.exchangeRates.observe(this) { exchangeRates ->


            val exchangeRatesString = StringBuilder()
            for ((currency, rate) in exchangeRates) {
                exchangeRatesString.append(currency).append(": ").append(rate).append("\n")
            }
            ratesTextView.text = exchangeRatesString.toString()


        }

        viewModel.fetchExchangeRates()
    }
}