package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Favorite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Favorite.Adapter.FavoritAdapter
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = FavoritViewsModelsFactory.getInstance(application)
        val viewModel: FavoritViewsModels = ViewModelProvider(this,factory)[FavoritViewsModels::class.java]

        val adapter = FavoritAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavorite.addItemDecoration(itemDecoration)

        viewModel.getAllFavorites().observe(this){list ->
            if (list != null){
                adapter.submitList(list)
                binding.rvFavorite.adapter = adapter
            }

            if (list.isEmpty()){
                Toast.makeText(
                    this,
                    "There are no favorite users",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}