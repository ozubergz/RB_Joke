package com.example.rb_joke.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rb_joke.databinding.ActivityJokeBinding
import com.example.rb_joke.model.Joke
import com.example.rb_joke.util.Constants
import com.example.rb_joke.viewmodel.ViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class JokeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val joke = intent.getStringExtra(Constants.JOKE_KEY)

        var obj = joke?.let { Json.decodeFromString<Joke>(it) }
        obj = obj as Joke

        binding.tvCategoryName.text = obj.category
        binding.tvSafe.text = "safe: ${obj.safe}"

        if(obj.type == "single") {
            binding.tvSetUp.text = "Joke: ${obj.joke}"
            binding.tvDelivery.visibility = View.GONE
        } else {
            binding.tvSetUp.text = "Setup: ${obj.setup}"
            binding.tvDelivery.text = "Delivery: ${obj.delivery}"
            binding.tvDelivery.visibility = View.VISIBLE
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("CDA", "clicked")
        finish()
        return super.onSupportNavigateUp()
    }
}