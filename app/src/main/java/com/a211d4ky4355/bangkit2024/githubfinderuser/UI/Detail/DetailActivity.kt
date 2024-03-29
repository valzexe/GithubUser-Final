package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseUser
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers
import com.a211d4ky4355.bangkit2024.githubfinderuser.R
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.Tab.Adapter.SectionsPagerAdapter
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var receivedData: String? = null
    private var bookmark  = false
    private val favorite = FavoriteUsers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra(USER)) {
            receivedData = receivedIntent.getStringExtra(USER).toString()
        }

        val viewModel: DetailViewsModel by viewModels {
            DetailViewsModelFactory(application)
        }

        viewModel.loading().observe(this) {
            showLoading(it)
        }

        viewModel.detailUser(receivedData.toString())

        viewModel.user().observe(this){user ->
            user?.let {
                setDetailData(it)
                favorite.login = it.login
                favorite.id = receivedIntent.getIntExtra(KEY_ID, 0)
                favorite.avatarUrl = it.avatarUrl
            }
        }

        receivedData?.let { getViewPager(it) }

        viewModel.getFavoriteByUsername(receivedData.toString()).observe(this) { favUser ->
            if (favUser != null) {
                binding.fabAdd.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.fabAdd.context,
                        R.drawable.baseline_favorite_24
                    )
                )
                bookmark = true
            } else {
                binding.fabAdd.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.fabAdd.context,
                        R.drawable.baseline_favorite_border_24
                    )
                )
                bookmark = false
            }
        }

        binding.fabAdd.setOnClickListener {
            inBookmark(viewModel)
        }
    }

    private fun inBookmark(viewModel:DetailViewsModel){
        if (bookmark) {
            viewModel.delete(favorite)
            Toast.makeText(
                this@DetailActivity,
                getString(R.string.hapus_fav,favorite.login.toString()),
                Toast.LENGTH_LONG
            ).show()
        } else {
            viewModel.insert(favorite)
            Toast.makeText(
                this@DetailActivity,
                getString(R.string.tambah_fav,favorite.login.toString()),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setDetailData(responseBody: ResponseUser){
        binding.tvNameDetail.text = responseBody.name
        binding.tvUsername.text = responseBody.login
        Glide.with(this@DetailActivity)
            .load(responseBody.avatarUrl)
            .into(binding.ivImageProfile)
        binding.tvFollowers.text = getString(R.string.follower_amnt,responseBody.followers.toString())
        binding.tvFollowing.text = getString(R.string.following_amnt,responseBody.following.toString())
    }


    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun getViewPager(username: String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.setUsername(username)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )

        const val USER = "user"
        const val KEY_ID = "usr_id"
    }

}