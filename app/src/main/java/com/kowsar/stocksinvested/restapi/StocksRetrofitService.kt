package com.kowsar.stocksinvested.restapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

object StocksRetrofitService {
    var BASE_URL = "https://run.mocky.io/v3/"
    private var retrofit: Retrofit? = null

    fun getApiService(): StocksHoldingAPI? {
        val logging = HttpLoggingInterceptor()
        val level = HttpLoggingInterceptor.Level.BODY
        logging.apply {
            this.level = level
        }
        val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient) // to print okhttp log on Logcat.
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(StocksHoldingAPI::class.java)
    }
}