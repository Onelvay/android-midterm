package com.example.tickets.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.adapter.OfferListAdapter
import com.example.tickets.databinding.ActivityMainBinding
import com.example.tickets.viewmodel.OfferViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: OfferListAdapter
    private val viewModel: OfferViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeOffers()
        setupSortOptions()
    }

    private fun setupRecyclerView() {
        adapter = OfferListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeOffers() {
        lifecycleScope.launch {
            viewModel.offers.collect { offers ->
                adapter.submitList(offers)
            }
        }
    }

    private fun setupSortOptions() {
        binding.radioButtonPrice.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.sortOffersByPrice()
            }
        }

        binding.radioButtonDuration.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.sortOffersByDuration()
            }
        }
    }
}
