package com.example.tickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.adapter.OfferListAdapter
import com.example.tickets.databinding.FragmentOfferListBinding
import com.example.tickets.viewmodel.OfferViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OfferListFragment : Fragment() {

    private var _binding: FragmentOfferListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OfferViewModel by viewModels()  // Используем viewModels() для получения ViewModel

    private lateinit var adapter: OfferListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfferListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeOffers()
        setupSortOptions()
    }

    private fun setupRecyclerView() {
        adapter = OfferListAdapter()
        binding.offerList.layoutManager = LinearLayoutManager(requireContext())
        binding.offerList.adapter = adapter
    }

    private fun observeOffers() {
        lifecycleScope.launch {
            viewModel.offers.collect { offers ->
                adapter.submitList(offers)
            }
        }
    }

    private fun setupSortOptions() {
        binding.sortByPrice.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.sortOffersByPrice()
            }
        }

        binding.sortByDuration.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.sortOffersByDuration()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
