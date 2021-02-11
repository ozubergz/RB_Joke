package com.example.rb_joke.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_joke.adapter.Adapter
import com.example.rb_joke.adapter.ClickListener
import com.example.rb_joke.databinding.ActivityMainBinding
import com.example.rb_joke.util.Constants
import com.example.rb_joke.viewmodel.ViewModel

class MainActivity : AppCompatActivity(), ClickListener {

    private val viewModel by viewModels<ViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var categories: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //REMEMBER TO PASS BINDING.ROOT TO SETCONTENTVIEW

        viewModel.fetchAPICategories()

        viewModel.categories.observe(this) {

            categories = it.categories

            val adapter = Adapter(it.categories, this)
            adapter.setViewType(0)

            binding.rvCategoryList.adapter = adapter
            binding.rvCategoryList.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun itemClick(position: Int) {
        val intent = Intent(this, JokeActivity::class.java)
        intent.putExtra(Constants.CATEGORY_KEY, categories[position])
        startActivity(intent)
    }
}