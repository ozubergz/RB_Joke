package com.example.rb_joke.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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
    private val map = HashMap<Int, String>()
    private var selectedCategories = HashMap<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //REMEMBER TO PASS BINDING.ROOT TO SETCONTENTVIEW

        viewModel.fetchAPICategories()

        initObservers()
        initListeners()
    }

    private fun initObservers() {

        viewModel.categories.observe(this) {

            categories = it.categories

            val adapter = Adapter(it.categories, this)
            adapter.setViewType(0)

            binding.rvCategoryLinearList.adapter = adapter
            binding.rvCategoryGridList.adapter= adapter
        }

        binding.rvCategoryLinearList.layoutManager = LinearLayoutManager(this)
        binding.rvCategoryGridList.layoutManager = GridLayoutManager(this, 3)
        binding.rvCategoryGridList.visibility = View.GONE
    }

    private fun initListeners() {
        binding.btnLinear.setOnClickListener {
            binding.rvCategoryLinearList.visibility = View.VISIBLE
            binding.rvCategoryGridList.visibility = View.GONE
        }

        binding.btnGrid.setOnClickListener {
            binding.rvCategoryGridList.visibility = View.VISIBLE
            binding.rvCategoryLinearList.visibility = View.GONE
        }

        binding.btnSubmit.setOnClickListener {

            var stringOfCategories = ""
            map.forEach { (k, v) ->
                stringOfCategories += "${v},"
            }

            if(stringOfCategories.isNotEmpty()) {
                val intent = Intent(this, JokeListActivity::class.java)
                intent.putExtra(Constants.CATEGORY_KEY, stringOfCategories.dropLast(1))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Make sure to choose categoreis", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun itemClick(position: Int) {
        if(map.containsKey(position)) {
            map.keys.remove(position)
        } else {
            map[position] = categories[position]
        }
        selectedCategories = map
    }
}