package com.example.tickets.adapter

import retrofit2.http.GET
import com.example.tickets.model.entity.Offer

interface OfferApi {
    @GET("offers")
    suspend fun getOffers(): List<Offer>
}
