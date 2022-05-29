package com.kowsar.stocksinvested.adapter

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.kowsar.stocksinvested.R
import com.kowsar.stocksinvested.data.Stocks
import com.kowsar.stocksinvested.model.Blog
import com.kowsar.stocksinvested.viewmodel.StocksViewModel

class StocksRecyclerAdapter (val viewModel: StocksViewModel, val arrayList: ArrayList<Stocks>, val context: Context): RecyclerView.Adapter<StocksRecyclerAdapter.StocksViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): StocksRecyclerAdapter.StocksViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return StocksViewHolder(root)
    }

    override fun onBindViewHolder(holder: StocksRecyclerAdapter.StocksViewHolder, position: Int) {
        holder.updateView(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayList.size
    }


    inner class StocksViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val company: TextView = binding.findViewById(R.id.company)
        val quantity: TextView = binding.findViewById(R.id.quantity)
        val ltp: TextView = binding.findViewById(R.id.ltp)
        val pl: TextView = binding.findViewById(R.id.pl)


        fun updateView(data: Stocks) {
            company.text = data.symbol
            quantity.text = data.quantity.toString()
            val txtLTP = SpannableStringBuilder()
                .append(context.resources.getString(R.string.ltp))
                .append(" ")
                .bold { append(context.resources.getString(R.string.rupee)) }
                .append(" ")
                .bold {append(data.ltp.toString())}
            ltp.text = txtLTP //
            // " ${context.resources.getString(R.string.ltp)} ${data.ltp.toString()}"
            val plIndividual = data.avgPrice?.let { data.ltp?.minus(it.toDouble()) }
            val totalPL = plIndividual?.let { it * data.quantity!! }
            val txtLP = SpannableStringBuilder()
                .append(context.resources.getString(R.string.pl))
                .append(" ")
                .bold { append(context.resources.getString(R.string.rupee)) }
                .append(" ")
                .bold {append(String.format("%.2f", totalPL))}
            pl.text = txtLP //"${context.resources.getString(R.string.pl)} ${String.format("%.2f", totalPL)}"


        }

    }
}

