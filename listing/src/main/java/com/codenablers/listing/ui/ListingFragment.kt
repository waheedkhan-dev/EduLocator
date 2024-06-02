package com.codenablers.listing.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codenablers.core.domain.Result
import com.codenablers.listing.ListingAdapter
import com.codenablers.listing.databinding.FragmentListingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingFragment : Fragment() {
    private lateinit var listingAdapter: ListingAdapter
    private val viewModel: ListingViewModel by viewModels()
    private lateinit var listingFragment: FragmentListingBinding
    private val binding get() = listingFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listingFragment = FragmentListingBinding.inflate(inflater, container, false)
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getUniversity().collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            // Show loading indicator
                            loadingProgressBar.visibility = View.VISIBLE
                            listingRecyclerView.visibility = View.INVISIBLE
                            textViewEmptyData.visibility = View.INVISIBLE
                        }

                        is Result.Success -> {
                            loadingProgressBar.visibility = View.INVISIBLE
                            textViewEmptyData.visibility = View.INVISIBLE
                            listingRecyclerView.visibility = View.VISIBLE
                            val universities = result.data
                            if (universities.isNotEmpty()) {
                                listingAdapter = ListingAdapter(onItemClicked = {
                                    //Navigate to details fragment also communicating to other module via deeplink with parameters
                                    val uri = Uri.parse("eduLocator://detailsFragment/${it.id}")
                                    findNavController().navigate(uri)
                                })
                                val recyclerView = binding.listingRecyclerView
                                recyclerView.layoutManager =
                                    LinearLayoutManager(
                                        requireContext(),
                                        RecyclerView.VERTICAL,
                                        false
                                    )
                                recyclerView.adapter = listingAdapter
                                listingAdapter.saveData(universities)
                            }
                        }

                        is Result.Error -> {
                            textViewEmptyData.visibility = View.VISIBLE
                            loadingProgressBar.visibility = View.INVISIBLE
                            listingRecyclerView.visibility = View.INVISIBLE
                            Toast.makeText(
                                requireContext(),
                                result.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
        return binding.root
    }
}