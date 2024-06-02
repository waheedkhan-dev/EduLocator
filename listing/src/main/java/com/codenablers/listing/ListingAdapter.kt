package com.codenablers.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codenablers.core.data.models.UniversitiesResponse
import com.codenablers.listing.databinding.ListingLayoutBinding

class ListingAdapter(private val onItemClicked: (UniversitiesResponse.University) -> Unit) :
    RecyclerView.Adapter<ListingAdapter.CharactersAdapterVh>() {
    class CharactersAdapterVh(var binding: ListingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    private val diffUtil =
        object : DiffUtil.ItemCallback<UniversitiesResponse.University>() {
            override fun areItemsTheSame(
                oldItem: UniversitiesResponse.University,
                newItem: UniversitiesResponse.University
            ):
                    Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UniversitiesResponse.University,
                newItem: UniversitiesResponse.University
            ):
                    Boolean {
                return oldItem == newItem
            }
        }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun saveData(dataResponse: List<UniversitiesResponse.University>) {
        asyncListDiffer.submitList(dataResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CharactersAdapterVh {
        val binding =
            ListingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersAdapterVh(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapterVh, position: Int) {
        val data = asyncListDiffer.currentList[position]
        holder.binding.apply {
            textViewUniName.text = data.name
            textViewStateName.text = data.stateProvince
            listingCardView.setOnClickListener {
                onItemClicked(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }
}