package com.banty.detail

import android.content.Context
import android.content.Intent

/**
 * Created by Banty on 2019-04-30.
 */
class DetailFlow {

    fun showDetail(payload: HashMap<String, Any>, context: Context?) {
        if (payload.containsKey("cid")) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("cid", payload["cid"] as String)
            context?.startActivity(intent)
        }
    }
}