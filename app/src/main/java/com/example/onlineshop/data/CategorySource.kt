package com.example.onlineshop.data

import com.example.onlineshop.R
import com.example.onlineshop.data.models.Category

object CategorySource {
    fun getCategoryData(): List<Category> {
        return arrayListOf(
            Category(
              "Phones",
                R.drawable.ic_phone_20dp
                ),
            Category(
                "Headphones",
                R.drawable.ic_headphones_20dp
                ),
            Category(
                "Games",
                R.drawable.ic_games_20dp
                ),
            Category(
                "Cars",
                R.drawable.ic_cars_20dp
                ),
            Category(
                "Furniture",
                R.drawable.ic_furniture_20dp
                ),
            Category(
                "Kids",
                R.drawable.ic_kids_20dp
                )
        )
    }
}