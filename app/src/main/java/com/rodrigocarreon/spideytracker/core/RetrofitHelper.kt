package com.rodrigocarreon.spideytracker.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper  {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://spideytracker.vercel.app") //Visit now :)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}