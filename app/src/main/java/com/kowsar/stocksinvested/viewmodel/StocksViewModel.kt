package com.kowsar.stocksinvested.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kowsar.stocksinvested.data.StocksHolding
import com.kowsar.stocksinvested.repository.StcoksRepository

class StocksViewModel: ViewModel() {

    var stocksData: MutableLiveData<StocksHolding>? = null

    fun getHoldingStocks() : LiveData<StocksHolding>? {
        stocksData = StcoksRepository.getStocksdata()
        return stocksData
    }

}