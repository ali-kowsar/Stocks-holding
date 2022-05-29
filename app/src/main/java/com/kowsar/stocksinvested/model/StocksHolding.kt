package com.kowsar.stocksinvested.data

import com.google.gson.annotations.SerializedName


data class StocksHolding (

  @SerializedName("client_id"     ) var clientId     : String?         = null,
  @SerializedName("data"          ) var data         : ArrayList<Stocks> = arrayListOf(),
  @SerializedName("error"         ) var error        : String?         = null,
  @SerializedName("response_type" ) var responseType : String?         = null,
  @SerializedName("timestamp"     ) var timestamp    : Long?            = null



)