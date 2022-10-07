package com.enablon.instaware.data.remote

import com.enablon.instaware.data.remote.instagram.InstagramServices
import com.enablon.instaware.data.remote.quote.QuoteServices
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Network Retrofit client
 */
fun provideRetrofitClient(): Retrofit =
    Retrofit.Builder()
        .baseUrl(INSTAGRAM_BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

/**
 * An instance of the Instagram API
 */
fun provideInstagramApi(): InstagramServices =
    provideRetrofitClient().create(InstagramServices::class.java)

/**
 * An instance of the Quote API
 */
fun provideQuoteApi(): QuoteServices =
    provideRetrofitClient().create(QuoteServices::class.java)