package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.Tab.Adapter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.Tab.FollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private var username1: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_POSITION, position + 1)
            putString(FollowFragment.ARG_USERNAME, username1)
        }
        return fragment
    }
    override fun getItemCount(): Int {
        return 2
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setUsername(username: String) {
        username1 = username
        notifyDataSetChanged()
    }
}