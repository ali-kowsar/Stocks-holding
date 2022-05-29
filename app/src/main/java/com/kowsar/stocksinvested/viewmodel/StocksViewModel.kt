package com.kowsar.stocksinvested.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kowsar.stocksinvested.data.StocksHolding
import com.kowsar.stocksinvested.model.Blog
import com.kowsar.stocksinvested.repository.StcoksRepository

class StocksViewModel: ViewModel() {
    var lst = MutableLiveData<ArrayList<Blog>>()
    var newlist = arrayListOf<Blog>()

    var stocksData: MutableLiveData<StocksHolding>? = null

    fun getHoldingStocks() : LiveData<StocksHolding>? {
        stocksData = StcoksRepository.getServicesApiCall()
        return stocksData
    }

    fun add(blog: Blog){
        newlist.add(blog)
        lst.value=newlist
    }

    fun remove(blog: Blog){
        newlist.remove(blog)
        lst.value=newlist
    }

}