package com.bor96dev.mirbank


import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException

class ExchangeRateRepository() {
    private val client = OkHttpClient()

    fun getExchangeRates(callback: (Map<String, String>) -> Unit) {
        val url = "https://mironline.ru/support/list/kursy_mir/"
        val request = Request.Builder().url(url).build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //todo

            }

            override fun onResponse(call: Call, response: Response) {
                val htmlContent = response.body?.string()
                val doc = Jsoup.parse(htmlContent)
                val exchangeRates = doc.select("table tr")
                    .drop(1)
                    .map { row ->
                        val currency = row.select("td:nth-child(1)").text()
                        val rate = row.select("td:nth-child(2)").text().replace(",", ".")

                        currency to rate
                    }
                    .toMap()
                callback(exchangeRates)
            }
        })
    }
}