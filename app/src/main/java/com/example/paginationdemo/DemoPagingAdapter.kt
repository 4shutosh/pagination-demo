package com.example.paginationdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.databinding.ListItemDemoBinding

class DemoPagingAdapter: PagingDataAdapter<MainViewModel.ListViewState, DemoListViewHolder>(
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