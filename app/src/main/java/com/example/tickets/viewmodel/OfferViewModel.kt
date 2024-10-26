package com.example.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.repository.OfferRepository
import com.example.tickets.model.entity.Offer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OfferViewModel(private val repository: OfferRepository) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    init {
        fetchOffers()
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            val offerList = repository.getOffers()
            _offers.value = offerList
        }
    }

    fun sortOffersByPrice() {
        _offers.value = _offers.value.sortedBy { it.price }
    }

    fun sortOffersByDuration() {
        _offers.value = _offers.value.sortedBy { it.flight.duration }
    }
}
