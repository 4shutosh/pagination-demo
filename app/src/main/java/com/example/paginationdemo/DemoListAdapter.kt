package com.example.paginationdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.databinding.ListItemDemoBinding

class DemoListAdapter:
    ListAdapter<MainViewModel.ListViewState, DemoListViewHolder>(
        DemoDiffCallback) {

    override fun onBindViewHolder(holder: DemoListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.binding.apply {
                itemTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoListViewHolder {
        return DemoListViewHolder(
            ListItemDemoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}

class DemoListViewHolder(val binding: ListItemDemoBinding) :
    RecyclerView.ViewHolder(binding.root)

object DemoDiffCallback : DiffUtil.ItemCallback<MainViewModel.ListViewState>() {
    override fun areItemsTheSame(
        oldItem: MainViewModel.ListViewState,
        newItem: MainViewModel.ListViewState,
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: MainViewModel.ListViewState,
        newItem: MainViewModel.ListViewState,
    ) = oldItem == newItem

}