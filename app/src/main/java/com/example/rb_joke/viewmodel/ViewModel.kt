package com.example.rb_joke.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rb_joke.model.Categories
import com.example.rb_joke.model.Joke
import com.example.rb_joke.model.Jokes
import com.example.rb_joke.repo.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    private val repo = Repo.service

    private val _categories = MutableLiveData<Categories>()
    private val _jokes = MutableLiveData<Jokes>()

    val categories: LiveData<Categories>
        get() = _categories

    val jokes: LiveData<Jokes>
        get() = _jokes

    fun fetchAPICategories() {
        repo.getCategories().enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                _categories.value = response.body()
            }
            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.e("Fetch Categories", t.message, t)
            }
        })
    }

    fun fetchAPIJokes(category: String, amount: Int) {
        repo.getJokes(category, amount).enqueue(object : Callback<Jokes> {
            override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {
                _jokes.value = response.body()
            }
            override fun onFailure(call: Call<Jokes>, t: Throwable) {
                Log.e("Jokes Categories", t.message, t)
            }
        })
    }

}