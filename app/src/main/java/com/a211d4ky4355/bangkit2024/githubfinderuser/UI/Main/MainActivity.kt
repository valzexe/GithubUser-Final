package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference
import com.a211d4ky4355.bangkit2024.githubfinderuser.R
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Favorite.FavoriteActivity
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Main.Adapter.GithubAdapter
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Setting.SettingsModeActivity
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var viewModel: MainViewsModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvUserItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUserItem.addItemDecoration(itemDecoration)

        val pref = SettingsPerference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, MainViewsModelsFactory(pref))[MainViewsModels::class.java]


        modeCheck()

        viewModel.getItems("valzexe")

        viewModel.user().observe(this){
            setReviewData(it)
        }

        viewModel.loading().observe(this){
            showLoading(it)
        }


        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    viewModel.getItems(searchView.text.toString())
                    viewModel.user().observe(this@MainActivity){
                        setReviewData(it)
                    }
                    false
                }
        }

        binding.searchBar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId){
                R.id.menu1 ->{
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu2 ->{
                    val intent = Intent(this, SettingsModeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun modeCheck() {
        viewModel.getThemeSettings().observe(this){ isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setReviewData(user: List<Items>) {
        val adapter = GithubAdapter()
        adapter.submitList(user)
        binding.rvUserItem.adapter = adapter
    }
}