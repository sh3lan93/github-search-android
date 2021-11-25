package com.shalan.searchgithub.base.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<viewBinding : ViewDataBinding, T>(
    protected val binding: viewBinding,
    protected val listener: ItemListener? = null
) :
    RecyclerView.ViewHolder(binding.root) {


    fun baseBinding(item: T) {
        binding.root.setOnClickListener {
            mutateUI(item)
            listener?.onItemClicked(bindingAdapterPosition)
        }
        bind(item)
        binding.executePendingBindings()
    }

    abstract fun bind(item: T)

    open fun mutateUI(item: T) {

    }
}
