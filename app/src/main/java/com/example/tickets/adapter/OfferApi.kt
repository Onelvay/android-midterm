package com.example.tickets.adapter

import com.example.tickets.model.entity.Offer
import retrofit2.http.GET

interface OfferApi {
    @GET("offer_list")
    suspend fun getOffers(): List<Offer>
}
