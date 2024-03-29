package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Main.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.DetailActivity
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.GithubUserItemBinding
import com.bumptech.glide.Glide

class GithubAdapter:ListAdapter<Items, GithubAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder (private val binding: GithubUserItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Items){
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .into(binding.ivProfilePicture)
            binding.tvName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =GithubUserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listUser = getItem(position)
        holder.bind(listUser)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.USER, listUser.login)
            intent.putExtra(DetailActivity.KEY_ID,listUser.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>(){
            override fun areItemsTheSame(
                oldItem: Items,
                newItem: Items
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Items,
                newItem: Items
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}