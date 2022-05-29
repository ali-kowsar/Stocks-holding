package com.kowsar.stocksinvested.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kowsar.stocksinvested.R
import com.kowsar.stocksinvested.adapter.StocksRecyclerAdapter
import com.kowsar.stocksinvested.data.Stocks
import com.kowsar.stocksinvested.viewmodel.StocksViewModel
import com.kowsar.stocksinvested.viewmodel.StocksViewModelFactory

class MainActivity : AppCompatActivity() {
    private var TAG = MainActivity::class.java.simpleName
    private var mHandler: Handler? = null
    lateinit var currentVal:TextView
    lateinit var totalInvestment:TextView
    lateinit var todaysPL:TextView
    lateinit var totalPL:TextView
    lateinit var loadingTimeout:TextView
    lateinit var pb: ProgressBar
    lateinit var progressLayout:FrameLayout

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModelStocks: StocksViewModel
    private lateinit var recyclerViewSH: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        initViews()

        mHandler = Handler()
        mHandler!!.postDelayed(Runnable {
            pb.visibility = View.GONE
            loadingTimeout.visibility = View.VISIBLE
        },5000)

        recyclerViewSH.layoutManager = viewManager
        val factory = StocksViewModelFactory()
        viewModelStocks = ViewModelProviders.of(this, factory).get(StocksViewModel::class.java)
        viewModelStocks.getHoldingStocks()!!.observe(this, Observer { stocksHolding ->
            mHandler!!.removeCallbacksAndMessages(null)
            var stocks = stocksHolding.data
            val currentValue = getCurrentVal(stocks)
            val totalInvestmentVal = getTotalInvestment(stocks)
            val todaysPLVal= getTodaysPL(stocks)
            currentVal.text = "${resources.getString(R.string.rupee)} ${String.format("%.2f", currentValue)}"
            totalInvestment.text = "${resources.getString(R.string.rupee)} ${String.format("%.2f", totalInvestmentVal)}"
            totalPL.text = "${resources.getString(R.string.rupee)} ${(currentValue?.minus( totalInvestmentVal)).toString()}"
            todaysPL.text = "${resources.getString(R.string.rupee)} ${String.format("%.2f", todaysPLVal)}"
            recyclerViewSH.adapter = StocksRecyclerAdapter(viewModelStocks, stocks, this)
            recyclerViewSH.adapter?.notifyDataSetChanged()
            progressLayout.visibility = View.GONE

        })

    }

    private fun getTodaysPL(stocks: ArrayList<Stocks>): Double {
        var todaysPL = 0.0
        for(stock :Stocks in stocks){
            val profitAndLoss= stock.close?.minus(stock.ltp!!).let { it?.times(stock.quantity!!) }
            todaysPL = todaysPL+profitAndLoss!!
        }
        return  todaysPL
    }

    private fun getTotalInvestment(stocks: ArrayList<Stocks>): Double {
        var totalInvestment = 0.0
        for(stock :Stocks in stocks){
            val investment= stock.avgPrice?.let { it.toDouble()*stock.quantity!! }
            totalInvestment = totalInvestment+investment!!
        }
        return  totalInvestment

    }

    private fun getCurrentVal(stocks: ArrayList<Stocks>): Double? {
        var ltpVal = 0.0
        for(stock :Stocks in stocks){
            val ltp= stock.ltp?.let { it*stock.quantity!! }
            ltpVal = ltpVal+ltp!!
        }

        return ltpVal
    }

    private fun initViews() {
        Log.v(TAG, "initViews(): Enter")
        setSupportActionBar(findViewById(R.id.toolbar))
        recyclerViewSH = findViewById(R.id.recycler)
        currentVal=findViewById(R.id.currentval)
        totalInvestment = findViewById(R.id.total_investment)
        todaysPL = findViewById(R.id.todays_pl)
        totalPL = findViewById(R.id.total_pl)
        loadingTimeout = findViewById(R.id.loading_timeout)
        pb = findViewById(R.id.loading_progress)
        progressLayout = findViewById(R.id.fl_loading_progress)
    }

}