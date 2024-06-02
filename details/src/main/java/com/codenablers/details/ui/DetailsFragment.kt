package com.codenablers.details.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codenablers.details.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModels()

    private lateinit var detailsFragmentBinding: FragmentDetailsBinding
    private val binding get() = detailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsFragmentBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            arguments?.let {
                // Get the university ID from the arguments
                val id = it.getInt("id")
                lifecycleScope.launch {
                    // Call the ViewModel to fetch the university details from the database
                    detailsViewModel.getUniversityById(universityId = id)
                        .flowWithLifecycle(lifecycle = lifecycle, Lifecycle.State.STARTED).collect { university->

                        textViewUniName.text = university.name
                        textViewStateName.text = university.stateProvince
                        textViewCountryName.text = university.country
                        textViewCountryCode.text = university.alphaTwoCode
                            textViewWebPage.movementMethod = LinkMovementMethod.getInstance()
                        textViewWebPage.text = university.webPages.first()
                    }
                }
            }
            imageViewRefresh.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}