package com.example.onlineshop.domain.repository

import com.example.onlineshop.data.models.FlashSaleResponse
import com.example.onlineshop.data.models.LatestResponse
import retrofit2.Response

interface ProductRepository {
    suspend fun getLatest(): Response<LatestResponse>
    suspend fun getFlashSale(): Response<FlashSaleResponse>
}