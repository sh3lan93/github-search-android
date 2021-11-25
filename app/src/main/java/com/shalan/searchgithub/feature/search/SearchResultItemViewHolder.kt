package com.shalan.searchgithub.feature.search

import com.shalan.searchgithub.base.viewholder.BaseViewHolder
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import com.shalan.searchgithub.databinding.SearchResultItemViewHolderBinding

class SearchResultItemViewHolder(binding: SearchResultItemViewHolderBinding) :
    BaseViewHolder<SearchResultItemViewHolderBinding, GithubRepo>(binding) {

    override fun bind(item: GithubRepo) {
        binding.item = item
    }
}