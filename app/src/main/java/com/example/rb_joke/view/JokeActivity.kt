package com.example.rb_joke.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_joke.adapter.Adapter
import com.example.rb_joke.adapter.ClickListener
import com.example.rb_joke.databinding.ActivityMainJokeBinding
import com.example.rb_joke.util.Constants
import com.example.rb_joke.viewmodel.ViewModel

class JokeActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityMainJokeBinding
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category: String? = intent.getStringExtra(Constants.CATEGORY_KEY)

        if (category != null) {
            viewModel.fetchAPIJokes(category, 10)
        }

        viewModel.jokes.observe(this) {

            val adapter = Adapter(it.jokes, this)
            adapter.setViewType(1)

            binding.rvJokeList.adapter = adapter
            binding.rvJokeList.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }
}