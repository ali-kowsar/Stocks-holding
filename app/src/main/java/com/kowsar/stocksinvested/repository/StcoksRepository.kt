package com.kowsar.stocksinvested.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kowsar.stocksinvested.data.StocksHolding
import com.kowsar.stocksinvested.restapi.StocksRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object StcoksRepository {
    private val TAG = StcoksRepository::class.java.simpleName
    val stcoksHolding = MutableLiveData<StocksHolding>()

    fun getServicesApiCall(): MutableLiveData<StocksHolding> {

        val call = StocksRetrofitService.getApiService()?.getServices()

        call?.enqueue(object : Callback<StocksHolding> {
            override fun onFailure(call: Call<StocksHolding>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v(TAG, "Failure ${t.message.toString()}")

            }

            override fun onResponse(
                call: Call<StocksHolding>, response: Response<StocksHolding>
            ) {
                // TODO("Not yet implemented")
                Log.v(TAG, "Response: ${response.body().toString()}")
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse(): Response success. data=" + response.body())
                    stcoksHolding.postValue(response.body())
                }
            }
        })

        return stcoksHolding
    }
}