package com.example.rb_joke.repo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
* This gets you the instance of retrofit
* */

object RetrofitInstance {

    private const val BASE_URL = "https://sv443.net/jokeapi/v2/"

    private val logging = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        .let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(logging)
        .build()

    val service: Service by lazy { retrofit.create(Service::class.java) }

}