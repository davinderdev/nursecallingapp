package com.app.nursecalling.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private  val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://192.168.0.192/")
            .addConverterFactory(GsonConverterFactory
                .create()).build()
    }
    val apiInterface  by lazy {
        retrofit.create(ApiService::class.java)
    }
}