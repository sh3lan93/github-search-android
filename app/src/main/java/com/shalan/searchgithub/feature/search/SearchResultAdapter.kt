package com.shalan.searchgithub.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.shalan.searchgithub.R
import com.shalan.searchgithub.base.adapter.BaseAdapter
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import com.shalan.searchgithub.databinding.SearchResultItemViewHolderBinding

class SearchResultAdapter : BaseAdapter<GithubRepo, SearchResultItemViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem == newItem

        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder =
        DataBindingUtil.inflate<SearchResultItemViewHolderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.search_result_item_view_holder,
            parent,
            false
        ).let {
            SearchResultItemViewHolder(it)
        }
}