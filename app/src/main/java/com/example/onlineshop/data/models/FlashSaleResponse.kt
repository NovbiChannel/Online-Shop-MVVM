package com.example.onlineshop.data.models

data class FlashSaleResponse (
    val flashSale: List<FlashSale>,
    val status: String,
    val totalResult: Int
        )