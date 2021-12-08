package com.yogi.githubmedia.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.yogi.githubmedia.R
import com.yogi.githubmedia.data.networking.Responses
import com.yogi.githubmedia.models.Github
import com.yogi.githubmedia.ui.detail.GithubDetailActivity
import kotlinx.android.synthetic.main.fragment_following.*

/**
 * A simple [Fragment] subclass.
 */
class FollowingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    private lateinit var pBar: ProgressBar
    private lateinit var rvGithub: RecyclerView
    private var list: ArrayList<Github> = arrayListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pBar = following_load

        val github =
            activity?.intent?.getParcelableExtra(GithubDetailActivity.DATA_GITHUB) as Github
        val key = github.username

        AndroidNetworking.initialize(context)

        rvGithub = rv_github_following
        rvGithub.setHasFixedSize(true)

        rvGithub.layoutManager = LinearLayoutManager(context)
        Responses().getGithubFollowing(list, rvGithub, key, pBar)
    }

}
