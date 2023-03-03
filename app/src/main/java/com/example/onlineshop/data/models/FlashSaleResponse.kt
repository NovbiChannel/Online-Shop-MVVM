package com.example.onlineshop.data.models

data class FlashSaleResponse (
    val flash_sale: List<FlashSale>,
    val status: String,
    val totalResult: Int
        )