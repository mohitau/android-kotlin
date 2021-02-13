package com.app.instagramclone

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    val getClient: ApiInterface
        get() {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitBuilder.create(ApiInterface::class.java)
        }
}