package com.example.onlineshop.data.models

data class LatestResponse (
    val latest: List<Latest>,
    val status: String,
    val totalResult: Int
        )