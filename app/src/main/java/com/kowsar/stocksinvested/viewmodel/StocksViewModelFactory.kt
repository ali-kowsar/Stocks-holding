package com.kowsar.stocksinvested.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StocksViewModelFactory (): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StocksViewModel::class.java)){
            return StocksViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}