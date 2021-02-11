package com.example.rb_joke.repo

import com.example.rb_joke.model.Categories
import retrofit2.Call
import retrofit2.http.GET


interface Service {

    @GET("categories")
    fun getCategories() : Call<Categories>
}