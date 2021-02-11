package com.example.rb_joke.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jokes(
    val error: Boolean?,
    val amount: Int?,
    val jokes: List<Joke>
)