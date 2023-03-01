package com.example.onlineshop.data.repository

import com.example.onlineshop.data.api.MockyApi
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val mockyApi: MockyApi
){

    suspend fun getLatest() = mockyApi.getLatest()
    suspend fun getFlashSale() = mockyApi.getFlashSale()
}