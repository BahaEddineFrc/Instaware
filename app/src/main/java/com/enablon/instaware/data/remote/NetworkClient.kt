package com.enablon.instaware.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object {
        const val BASE_URL = "https://graph.instagram.com/"
    }

    private var getClient: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var instaServices: InstagramServices = getClient.create(InstagramServices::class.java)
}