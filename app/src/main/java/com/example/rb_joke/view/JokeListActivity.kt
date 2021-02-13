package com.example.rb_joke.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_joke.R
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

        val categories = intent.getStringExtra(Constants.CATEGORY_KEY)
        if (categories != null) {
            viewModel.fetchAPIJokes(categories, 10)
        }

//        val callback = this.onBackPressedDispatcher.addCallback(this) {
//            onBackPressed()
//        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        initView()
        toggleLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("CDA", "clicked")
        finish()
        return super.onSupportNavigateUp()
    }


    private fun initView() {
        viewModel.jokes.observe(this) {
            jokes = it.jokes
//            Log.d("JokeListActivity", jokes.toString())

            val adapter = Adapter(jokes, this)
            adapter.setViewType(1)

            binding.rvJokeLinearList.adapter = adapter
            binding.rvJokeGridList.adapter = adapter
        }

        binding.rvJokeLinearList.layoutManager = LinearLayoutManager(this)
        binding.rvJokeGridList.layoutManager = GridLayoutManager(this, 3)
        binding.rvJokeGridList.visibility = View.GONE
    }

    private fun toggleLayout() {
        binding.btnLinear.setOnClickListener {
            binding.rvJokeLinearList.visibility = View.VISIBLE
            binding.rvJokeGridList.visibility = View.GONE
        }

        binding.btnGrid.setOnClickListener {
            binding.rvJokeGridList.visibility = View.VISIBLE
            binding.rvJokeLinearList.visibility = View.GONE
        }
    }

    override fun itemClick(position: Int) {
        val json = Json.encodeToString(jokes[position])
        val intent = Intent(this, JokeActivity::class.java)
        intent.putExtra(Constants.JOKE_KEY, json)
        startActivity(intent)
    }
}