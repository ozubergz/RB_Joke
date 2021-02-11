package com.example.rb_joke.repo

import com.example.rb_joke.model.Categories
import com.example.rb_joke.model.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {

    @GET("categories")
    fun getCategories() : Call<Categories>

    @GET("joke/{category}")
    fun getJokes(@Path(value = "category") category: String, @Query("amount") amount: Int): Call<Jokes>
}