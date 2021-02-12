package com.example.rb_joke.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_joke.adapter.Adapter
import com.example.rb_joke.adapter.ClickListener
import com.example.rb_joke.databinding.ActivityMainJokeBinding
import com.example.rb_joke.model.Joke
import com.example.rb_joke.util.Constants
import com.example.rb_joke.viewmodel.ViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



class JokeListActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityMainJokeBinding
    private val viewModel by viewModels<ViewModel>()
    private lateinit var jokes: List<Joke>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category: String? = intent.getStringExtra(Constants.CATEGORY_KEY)

        if (category != null) {
            viewModel.fetchAPIJokes(category, 10)
        }

        viewModel.jokes.observe(this) {

            jokes = it.jokes

            val adapter = Adapter(it.jokes, this)
            adapter.setViewType(1)

            binding.rvJokeList.adapter = adapter
            binding.rvJokeList.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun itemClick(position: Int) {
        val json = Json.encodeToString(jokes[position])
        val intent = Intent(this, JokeActivity::class.java)
        intent.putExtra(Constants.JOKE_KEY, json)
        startActivity(intent)
    }
}