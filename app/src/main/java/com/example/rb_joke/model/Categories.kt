package com.example.rb_joke.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Categories(
    val error: Boolean?,
    val categories: List<String>?,
    val timestamp: Long?
)