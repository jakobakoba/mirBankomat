package com.bor96dev.mirbank

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bor96dev.mirbank.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ExchangeRateViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(this)[ExchangeRateViewModel::class.java]
        viewModel.exchangeRates.observe(this) { exchangeRates ->

            exchangeRates["Армянский драм"].let { binding.currDramRuble.text = it }
            exchangeRates["Армянский драм"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubleDram.text = roundedResult
            }

            exchangeRates["Белорусский рубль"].let { binding.currBelarusRuble.text = it }
            exchangeRates["Белорусский рубль"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubleBelarus.text = roundedResult
            }
            exchangeRates["Вьетнамский донг"].let { binding.currVietnamRuble.text = it }
            exchangeRates["Вьетнамский донг"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubleVietnam.text = roundedResult
            }
            exchangeRates["Казахстанский тенге"].let { binding.currTengeRub.text = it }
            exchangeRates["Казахстанский тенге"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubTenge.text = roundedResult
            }
            exchangeRates["Кубинский песо"].let { binding.currCubanRub.text = it }
            exchangeRates["Кубинский песо"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubCuban.text = roundedResult
            }
            exchangeRates["Кыргызский сом"].let { binding.currKyrgyzRub.text = it }
            exchangeRates["Кыргызский сом"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubKyrgyz.text = roundedResult
            }
            exchangeRates["Таджикский сомони"].let { binding.currTajikRub.text = it }
            exchangeRates["Таджикский сомони"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubTajik.text = roundedResult
            }
            exchangeRates["Узбекский сум"].let { binding.currUzbekRub.text = it }
            exchangeRates["Узбекский сум"]?.let {
                val result = 1.0 / it.toDouble()
                val roundedResult = String.format("%.3f", result)
                binding.currRubUzbek.text = roundedResult
            }
        }

        binding.button.setOnClickListener {
            viewModel.fetchExchangeRates()
            val currentTime = System.currentTimeMillis()
            val formattedTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                Date(currentTime)
            )

            Toast.makeText(this, "Exchange rates updated at $formattedTime", Toast.LENGTH_SHORT)
                .show()
        }
    }
}






