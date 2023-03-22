package com.bor96dev.mirbank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bor96dev.mirbank.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ExchangeRateViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }



        viewModel = ViewModelProvider(this).get(ExchangeRateViewModel::class.java)
        viewModel.exchangeRates.observe(this) { exchangeRates ->


            exchangeRates["Армянский драм"].let { binding.currDramRuble.text = it }
            exchangeRates["Армянский драм"]?.let {

                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubleDram.text = roundedResult
            }

            val exchangeRatesString = StringBuilder()
            for ((currency, rate) in exchangeRates) {
                exchangeRatesString.append(currency).append(": ").append(rate).append("\n")
            }


        }

        viewModel.fetchExchangeRates()
    }
}








