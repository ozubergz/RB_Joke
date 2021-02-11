package com.example.rb_joke.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    val category: String?,
    val type: String?,
    val setup: String?,
    val delivery: String?,
    val id: Int?,
    val safe: Boolean?,
    val lang: String?,
    val joke: String?
)