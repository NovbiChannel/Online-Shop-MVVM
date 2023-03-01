package com.example.onlineshop.data.models

data class FlashSale(
    val category: String,
    val name: String,
    val price: Int,
    val image_url: String,
    val discount: Int,
)
