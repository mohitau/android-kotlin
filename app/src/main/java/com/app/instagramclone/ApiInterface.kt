package com.app.instagramclone

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/photos")
    fun getMainFeeds(): Call<List<MainResponse>>
}