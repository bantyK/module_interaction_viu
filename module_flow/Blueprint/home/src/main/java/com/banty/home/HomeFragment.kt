package com.banty.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banty.core.model.Clip
import com.banty.home.ui.IHomePresenter
import com.banty.home.ui.presenter.HomePresenter
import com.banty.home.ui.recycler_view.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Banty on 2019-05-02.
 */
class HomeFragment : Fragment(), IHomePresenter.HomeView {
    private lateinit var homePresenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Viu", "Home flow started")
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homePresenter = HomePresenter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePresenter.setupHomePage()
    }

    override fun configureHomeFeed() {
        context?.let { context ->
            setupRecyclerView(context, recyclerView_normal_feed, homePresenter.getHomeFeed())
        }
    }

    private fun setupRecyclerView(context: Context, recyclerView: RecyclerView?, clips: List<Clip>) {
        recyclerView?.let {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = RecyclerAdapter(context, clips)
        }
    }

    override fun showWatchlist() {
        context?.let { context ->
            recyclerView_watchlist.visibility = View.VISIBLE
            textView_watchlist_header.visibility = View.VISIBLE
            setupRecyclerView(context, recyclerView_watchlist, homePresenter.provideWatchListClips())
        }
    }

    override fun hideWatchlist() {
        recyclerView_watchlist.visibility = View.GONE
        textView_watchlist_header.visibility = View.GONE
    }

    override fun showRecentlyWatch() {
        context?.let { context ->
            recyclerView_recent.visibility = View.VISIBLE
            textView_recently_watched_header.visibility = View.VISIBLE
            setupRecyclerView(context, recyclerView_recent, homePresenter.getRecentlyWatchClips())
        }
    }

    override fun hideRecentlyWatch() {
        recyclerView_recent.visibility = View.GONE
        textView_recently_watched_header.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Viu", "Home flow destroyed")
    }

}