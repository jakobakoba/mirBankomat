package com.bor96dev.mirbank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExchangeRateViewModel : ViewModel() {
    private val repository = ExchangeRateRepository()

    private val _exchangeRates = MutableLiveData<Map<String, String>>()
    val exchangeRates: LiveData<Map<String, String>> = _exchangeRates

    fun fetchExchangeRates() {
        repository.getExchangeRates { rates ->
            _exchangeRates.postValue(rates)
        }
    }
}