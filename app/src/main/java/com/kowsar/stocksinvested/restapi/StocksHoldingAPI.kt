package com.kowsar.stocksinvested.restapi

import com.kowsar.stocksinvested.data.StocksHolding
import retrofit2.Call
import retrofit2.http.GET

interface StocksHoldingAPI {
    @GET("6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    fun getServices() : Call<StocksHolding>
}