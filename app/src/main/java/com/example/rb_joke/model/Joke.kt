package com.example.rb_joke.model


import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
data class Joke(
    val category: String?,
    val type: String?,
    val setup: String?,
    val delivery: String?,
    val joke: String?,
    val flags: Flags?,
    val id: Int?,
    val safe: Boolean?,
    val lang: String?
)