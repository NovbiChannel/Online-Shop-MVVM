package com.example.onlineshop.data.api

import android.telecom.Call
import com.example.onlineshop.data.models.FlashSale
import com.example.onlineshop.data.models.FlashSaleResponse
import com.example.onlineshop.data.models.Latest
import com.example.onlineshop.data.models.LatestResponse
import retrofit2.Response
import retrofit2.http.GET

interface MockyApi {

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): Response<LatestResponse>

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): Response<FlashSaleResponse>
}