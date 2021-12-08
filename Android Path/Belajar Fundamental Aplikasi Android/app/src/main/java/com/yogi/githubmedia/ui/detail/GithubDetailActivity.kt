package com.yogi.githubmedia.ui.detail

import android.content.ContentValues
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.yogi.githubmedia.R
import com.yogi.githubmedia.adapters.SectionsPagerAdapter
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.HTML_URL
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.IMAGE
import com.yogi.githubmedia.data.database.DatabaseContract.GithubColumns.Companion.USERNAME
import com.yogi.githubmedia.data.database.GithubHelper
import com.yogi.githubmedia.models.Github
import com.yogi.githubmedia.utils.ImgConvert
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONObject
import kotlin.math.abs


@Suppress("DEPRECATION")
class GithubDetailActivity : AppCompatActivity() {

    private lateinit var appBarLayout: AppBarLayout
    private var cashCollapseState: Pair<Int, Int>? = null
    private lateinit var titleToolbarText: ConstraintLayout
    private lateinit var titleToolbarTextSingle: ConstraintLayout
    private lateinit var invisibleTextViewWorkAround: TextView
    private lateinit var background: FrameLayout
    private var avatarAnimateStartPointY: Float = 0F
    private var avatarCollapseAnimationChangeWeight: Float = 0F
    private var isCalculated = false

    companion object {
        const val DATA_GITHUB = "data_github"

        const val SWITCH_BOUND = 0.8f
        const val TO_EXPANDED = 0
        const val TO_COLLAPSED = 1
        const val WAIT_FOR_SWITCH = 0
        const val SWITCHED = 1
    }

    private lateinit var htmlUrl: String
    private var baseUrl: String? = null
    private lateinit var pBar: ProgressBar
    private lateinit var githubHelper: GithubHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        githubHelper = GithubHelper.getInstance(applicationContext)


        bind()

        appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
                if (isCalculated.not()) {
                    avatarCollapseAnimationChangeWeight = 1 / (1 - avatarAnimateStartPointY)
                    isCalculated = true
                }
                /**/
                updateViews(abs(i / appBarLayout.totalScrollRange.toFloat()))
            })


        pBar = detail_load

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

        val github = intent.getParcelableExtra(DATA_GITHUB) as Github

        val key = github.username
        baseUrl = "https://api.github.com/users/$key"

        getDetailGithub()


    }


    private fun getDetailGithub() {
        pBar.visibility = View.VISIBLE
        AndroidNetworking.get(baseUrl)
            .setTag(this)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with Responses
                    val username = response["login"].toString()
                    val avatar = response["avatar_url"].toString()
                    val name = response["name"].toString()
                    val company = response["company"].toString()
                    val location = response["location"].toString()
                    val repository = response["public_repos"].toString()
                    val followers = response["followers"].toString()
                    val following = response["following"].toString()
                    val url = response["html_url"].toString()

                    //change username
                    tv_username.text = username
                    tv_username_mini.text = username

                    //change poster
                    Glide.with(img_avatar)
                        .load(avatar)
                        .placeholder(R.drawable.avatar_load)
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .apply(RequestOptions())
                        .into(img_avatar)

                    Glide.with(img_avatar_mini)
                        .load(avatar)
                        .placeholder(R.drawable.avatar_load)
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .apply(RequestOptions())
                        .into(img_avatar_mini)

                    //change name
                    if (name == "null") {
                        tv_value_name.text = "-"
                    } else {
                        tv_value_name.text = name
                    }

                    //change company
                    if (company == "null") {
                        tv_value_company.text = "-"
                    } else {
                        tv_value_company.text = company
                    }


                    //change follower
                    tv_value_follower.text = followers

                    //change following
                    tv_value_following.text = following

                    //change location
                    if (location == "null") {
                        tv_value_location.text = "-"
                    } else {
                        tv_value_location.text = location
                    }

                    //change repository
                    tv_value_repository.text = repository
                    pBar.visibility = View.GONE

                    //bind url
                    htmlUrl = url

                    val favRed: Drawable = btn_favorite.context.resources.getDrawable(R.drawable.ic_baseline_favorite_24_red)
                    val favWhite: Drawable = btn_favorite.context.resources.getDrawable(R.drawable.ic_baseline_favorite_24)

                    if (githubHelper.username(username) != "0"){
                        btn_favorite.setText(R.string.remove_from_favorite)
                        btn_favorite.setCompoundDrawablesWithIntrinsicBounds(favRed, null, null, null)
                    }else{
                        btn_favorite.setText(R.string.add_to_favorite)
                        btn_favorite.setCompoundDrawablesWithIntrinsicBounds(favWhite, null, null, null)
                    }

                    btn_favorite.setOnClickListener {
                        if (githubHelper.username(username) == "0"){
                            btn_favorite.setText(R.string.remove_from_favorite)
                            btn_favorite.setCompoundDrawablesWithIntrinsicBounds(favRed, null, null, null)
                            githubHelper.open()
                            val values = ContentValues()
                            values.put(IMAGE, ImgConvert.imageToBitmap(img_avatar))
                            values.put(USERNAME, tv_username.text.toString())
                            values.put(HTML_URL, htmlUrl)

                            githubHelper.insert(values)
                            githubHelper.close()
                        }else{
                            btn_favorite.setText(R.string.add_to_favorite)
                            btn_favorite.setCompoundDrawablesWithIntrinsicBounds(favWhite, null, null, null)
                            if (githubHelper.deleteByUsername(tv_username.text.toString())){

                                Toast.makeText(applicationContext, "Item $username Deleted", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(applicationContext, "Error Deleting", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }


    private fun bind() {
        appBarLayout = app_bar_layout
        titleToolbarText = tv_profile_name
        titleToolbarTextSingle = tv_constraint
        background = fl_background
        invisibleTextViewWorkAround = tv_workaround
    }

    private fun updateViews(offset: Float) {
        /* apply levels changes*/
        when (offset) {
            in 0.15F..1F -> {
                titleToolbarText.apply {
                    if (visibility != View.VISIBLE) visibility = View.VISIBLE
                    alpha = (1 - offset) * 0.35F
                }
            }

            in 0F..0.15F -> {
                titleToolbarText.alpha = (1f)
            }
        }

        /** collapse - expand switch*/
        when {
            offset < SWITCH_BOUND -> Pair(TO_EXPANDED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
            else -> Pair(TO_COLLAPSED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
        }.apply {
            when {
                cashCollapseState != null && cashCollapseState != this -> {
                    when (first) {
                        TO_EXPANDED -> {
                            /* set avatar on start position (center of parent frame layout)*/
                            /**/
                            background.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@GithubDetailActivity,
                                    R.color.color_transparent
                                )
                            )
                            /* hide top titles on toolbar*/
                            titleToolbarTextSingle.visibility = View.INVISIBLE
                        }
                        TO_COLLAPSED -> background.apply {
                            alpha = 0F
                            setBackgroundColor(
                                ContextCompat.getColor(
                                    this@GithubDetailActivity,
                                    R.color.colorPrimary
                                )
                            )
                            animate().setDuration(250).alpha(1.0F)

                            /* show titles on toolbar with animation*/
                            titleToolbarTextSingle.apply {
                                visibility = View.VISIBLE
                                alpha = 0F
                                animate().setDuration(500).alpha(1.0f)
                            }
                        }
                    }
                    cashCollapseState = Pair(first, SWITCHED)
                }
                else -> {
                    cashCollapseState = Pair(first, WAIT_FOR_SWITCH)
                }
            }
        }
    }

}