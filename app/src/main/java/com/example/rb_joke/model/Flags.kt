package com.example.rb_joke.model


import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable //change this to Parcelize
@JsonClass(generateAdapter = true)
data class Flags(
    val nsfw: Boolean?,
    val religious: Boolean?,
    val political: Boolean?,
    val racist: Boolean?,
    val sexist: Boolean?,
    val explicit: Boolean?
)