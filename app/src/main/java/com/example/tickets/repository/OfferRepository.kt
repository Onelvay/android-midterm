package com.example.tickets.repository

import com.example.tickets.model.entity.Offer
import com.example.tickets.adapter.OfferApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OfferRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/estharossa/fake-api-demo/") 
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(OfferApi::class.java)

    suspend fun getOffers(): List<Offer> {
        return api.getOffers()
    }
}
