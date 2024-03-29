package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.Tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail.Tab.Adapter.FollowAdapter
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private val followViewModel: FollowViewsModel by viewModels()

    private var position: Int = 0
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvListFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvListFollow.addItemDecoration(itemDecoration)

        followViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let { userData ->
                setGithubData(userData)
            }
        }

        followViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)?:""
        }
        if (position == 1){
            followViewModel.setFollowers(username)
        } else {
            followViewModel.setFollowing(username)
        }
    }

    private fun setGithubData(item: List<Items>) {
        val adapter = FollowAdapter()
        adapter.submitList(item)
        binding.rvListFollow.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        const val ARG_POSITION = "position"
        const val  ARG_USERNAME = "username"
    }
}