package com.example.rb_joke.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_joke.databinding.CategoryItemBinding
import com.example.rb_joke.databinding.JokeItemBinding
import com.example.rb_joke.model.Joke

class Adapter(private var dataSet: List<Any>, private var listener: ClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_JOKE = 1
    }

    private var VIEW_TYPE: Int = 0

    inner class CategoryViewHolder(private val binding: CategoryItemBinding, private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = dataSet[position]
            binding.tvCategoryName.text = item as String
            binding.root.setOnClickListener {
                listener.itemClick(position)
            }
        }
    }

    inner class JokeViewHolder(private val binding: JokeItemBinding, private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item: Joke = dataSet[position] as Joke
            if(item.type == "single") {
                binding.tvJoke.text = item.joke
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        Log.d("view type", viewType.toString())
        return when(viewType) {
            TYPE_CATEGORY -> {
                val view = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CategoryViewHolder(view, listener)
            }
            TYPE_JOKE -> {
                val view = JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                JokeViewHolder(view, listener)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CategoryViewHolder -> holder.bind(position)
            is JokeViewHolder -> holder.bind(position)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setViewType(type: Int) {
        VIEW_TYPE = type
    }

    override fun getItemViewType(position: Int): Int {
        var type = 0
        if(VIEW_TYPE == TYPE_CATEGORY) {
            type = TYPE_CATEGORY
        } else if(VIEW_TYPE == TYPE_JOKE) {
            type = TYPE_JOKE
        }
        return type
    }


}