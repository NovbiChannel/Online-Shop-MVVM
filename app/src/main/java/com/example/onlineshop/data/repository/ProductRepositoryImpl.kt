package com.example.onlineshop.data.repository

import com.example.onlineshop.data.api.MockyApi
import com.example.onlineshop.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: MockyApi
): ProductRepository {

    override suspend fun getLatest() = api.getLatest()

    override suspend fun getFlashSale() = api.getFlashSale()
}