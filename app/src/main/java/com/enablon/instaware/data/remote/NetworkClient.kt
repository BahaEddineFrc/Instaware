package com.enablon.instaware.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//val headers = mapOf()
fun provideRetrofitClient() =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideInstagramApi(): InstagramServices =
    provideRetrofitClient().create(InstagramServices::class.java)