package com.enablon.instaware.data.remote

import com.enablon.instaware.data.remote.instagram.InstagramServices
import com.enablon.instaware.data.remote.quote.QuoteServices
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofitClient() =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideInstagramApi(): InstagramServices =
    provideRetrofitClient().create(InstagramServices::class.java)

fun provideQuoteApi(): QuoteServices =
    provideRetrofitClient().create(QuoteServices::class.java)