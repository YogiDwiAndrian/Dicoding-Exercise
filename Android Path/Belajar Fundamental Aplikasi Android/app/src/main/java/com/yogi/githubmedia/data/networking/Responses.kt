package com.yogi.githubmedia.data.networking

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.yogi.githubmedia.R
import com.yogi.githubmedia.adapters.GithubFragmentAdapter
import com.yogi.githubmedia.adapters.GithubMainAdapter
import com.yogi.githubmedia.models.Github
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class Responses {

    fun searchGithubUsers(
        list: ArrayList<Github> = arrayListOf(),
        rvGithub: RecyclerView,
        key: String,
        context: Context,
        pBar: ProgressBar
    ) {
        val listGithub = ArrayList<Github>()
        pBar.visibility = View.VISIBLE

        AndroidNetworking.get("https://api.github.com/search/users?q={username}")
            .addPathParameter("username", key)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with Responses

                    val item = response.getJSONArray("items")
                    val check = response.getString("total_count")
                    if (check == "0") {
                        Toast.makeText(context, R.string.empty_load, Toast.LENGTH_LONG).show()
                        pBar.visibility = View.GONE
                    } else {
                        try {
                            Log.d("Respone : ", response.toString())
                            for (i in 0 until item.length()) {
                                val github = Github()
                                github.username = item.getJSONObject(i).getString("login")
                                github.url_html = item.getJSONObject(i).getString("html_url")
                                github.avatar = item.getJSONObject(i).getString("avatar_url")


                                listGithub.add(github)
                            }
                            list.addAll(listGithub)
                            rvGithub.adapter = GithubMainAdapter(list)
                            pBar.visibility = View.GONE
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })

    }

    fun getGithubFollowing(
        list: ArrayList<Github> = arrayListOf(),
        rvGithub: RecyclerView,
        key: String,
        pBar: ProgressBar
    ) {
        val listGithub = ArrayList<Github>()
        pBar.visibility = View.VISIBLE
        AndroidNetworking.get("https://api.github.com/users/$key/following")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with Responses
                    try {
                        Log.d("Response : ", response.toString())
                        for (i in 0 until response.length()) {
                            val github = Github()
                            github.username = response.getJSONObject(i).getString("login")
                            github.avatar = response.getJSONObject(i).getString("avatar_url")


                            listGithub.add(github)
                        }
                        list.addAll(listGithub)
                        rvGithub.adapter = GithubFragmentAdapter(list)
                        pBar.visibility = View.GONE
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }


                }

                override fun onError(error: ANError) {
                }
            })

    }

    fun getGithubFollowers(
        list: ArrayList<Github> = arrayListOf(),
        rvGithub: RecyclerView,
        key: String,
        pBar: ProgressBar
    ) {
        val listGithub = ArrayList<Github>()
        pBar.visibility = View.VISIBLE
        AndroidNetworking.get("https://api.github.com/users/$key/followers")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with Responses
                    try {
                        Log.d("Response : ", response.toString())
                        for (i in 0 until response.length()) {
                            val github = Github()
                            github.username = response.getJSONObject(i).getString("login")
                            github.avatar = response.getJSONObject(i).getString("avatar_url")


                            listGithub.add(github)
                        }
                        list.addAll(listGithub)
                        rvGithub.adapter = GithubFragmentAdapter(list)
                        pBar.visibility = View.GONE
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }


                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })

    }

    fun getGithubUsers(
        list: ArrayList<Github> = arrayListOf(),
        rvGithub: RecyclerView,
        pBar: ProgressBar
    ) {
        list.clear()
        val listGithub = ArrayList<Github>()
        pBar.visibility = View.VISIBLE
        AndroidNetworking.get("https://api.github.com/users")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with Responses
                    try {
                        Log.d("Responses : ", response.toString())
                        for (i in 0 until response.length()) {
                            val github = Github()
                            github.username = response.getJSONObject(i).getString("login")
                            github.url_html = response.getJSONObject(i).getString("html_url")
                            github.avatar = response.getJSONObject(i).getString("avatar_url")


                            listGithub.add(github)
                        }
                        list.addAll(listGithub)
                        rvGithub.adapter = GithubMainAdapter(list)
                        pBar.visibility = View.GONE
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }


                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })

    }

}