package com.banty.home

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Banty on 2019-04-30.
 */
class HomeFeed {

    fun getHome() : ArrayList<String> {
        return arrayListOf("clip1", "clip2", "clip3", "clip4", "clip5", "clip6")
    }

    fun showHomePage(context: Context?) {
        val contentList = getHome()
        val intent = Intent(context, HomeFeedActivity::class.java)
        intent.putStringArrayListExtra("content", contentList)
        context?.startActivity(intent)
    }

    fun getDifferentProgramming() {
        Log.d("TAG", "No clip id")
    }

}