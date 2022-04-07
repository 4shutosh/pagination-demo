package com.example.paginationdemo

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ManualPagingScrollListener(
    private val pageSize: Int,
) :
    RecyclerView.OnScrollListener() {

    private var currentPage = 1

    abstract fun loadMoreData(page: Int)

    // vertical scroll implies usage of dy only
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        // visibleItemCount + firstVisibleItemPosition >= totalItemCount
        // && is recyclerView scrolled down
        // && itemCount is greater than total page size

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
            && firstVisibleItemPosition >= 0
        ) {
            currentPage += 1
            loadMoreData(currentPage)
        }
    }
}