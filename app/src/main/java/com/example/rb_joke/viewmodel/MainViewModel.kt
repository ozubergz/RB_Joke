package com.example.rb_joke.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rb_joke.model.Categories
import com.example.rb_joke.repo.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _categories = MutableLiveData<Categories>()

    val categories: LiveData<Categories>
        get() = _categories

    init {
        Repo.service.getCategories().enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                response.body()
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.e("View Model", t.message, t)
            }
        })

    }

}