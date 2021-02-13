package com.app.instagramclone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter
    private val mContext= this
    private val mFeedsList = ArrayList<MainResponse>()
    private lateinit var mShimmerLayout: ShimmerFrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        getFeeds()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.main_recycler)
        mShimmerLayout = findViewById(R.id.shimmer_layout)
        mainRecyclerAdapter = MainRecyclerAdapter(mFeedsList, this)
        val linearLayoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = mainRecyclerAdapter

    }

    override fun onResume() {
        super.onResume()
        recyclerView.visibility = View.GONE
        mShimmerLayout.visibility = View.VISIBLE
        mShimmerLayout.startShimmerAnimation()
    }

    private fun getFeeds() {
//        return mRetrofitBuilder.apiInterface.getMainFeeds()
        val response: Call<List<MainResponse>> = RetrofitBuilder.getClient.getMainFeeds()
        response.enqueue(object : Callback<List<MainResponse>> {
            override fun onResponse(
                call: Call<List<MainResponse>>?,
                response: Response<List<MainResponse>>?
            ) {
                mFeedsList.addAll(response!!.body()!!)
                recyclerView.adapter?.notifyDataSetChanged()
                mShimmerLayout.stopShimmerAnimation()
                recyclerView.visibility = View.VISIBLE
                mShimmerLayout.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<MainResponse>>?, t: Throwable?) {
                mShimmerLayout.stopShimmerAnimation()
            }
        })
    }
}