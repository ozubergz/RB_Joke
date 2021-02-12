package com.example.rb_joke.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rb_joke.databinding.ActivityJokeBinding
import com.example.rb_joke.model.Joke
import com.example.rb_joke.util.Constants
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



        Log.d("Joke Activity", obj.toString())
    }
}