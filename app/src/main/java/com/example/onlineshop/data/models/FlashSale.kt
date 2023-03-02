package com.example.onlineshop.data.models

data class FlashSale(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int,
    val image_url: String
)
