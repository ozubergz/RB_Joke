package com.example.rb_joke.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.rb_joke.R
import com.example.rb_joke.databinding.ActivityMainBinding
import com.example.rb_joke.model.Categories
import com.example.rb_joke.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(R.layout.activity_main)

        viewModel.categories.observe(this) {
            Log.d("View", "Categories size ${it.categories?.size}")
        }

    }
}