package com.example.paginationdemo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationdemo.PseudoDataSource.getPseudoDataSourceListViewState

class DemoPagingSource(

    private val pageLimitSize: Int = 30,
) : PagingSource<Int, MainViewModel.ListViewState>() {
    override fun getRefreshKey(state: PagingState<Int, MainViewModel.ListViewState>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainViewModel.ListViewState> {
        // assuming some data source here: local db or online

        val nextPageNumber = params.key ?: 1

        return LoadResult.Page(
            data = getPseudoDataSourceListViewState(nextPageNumber),
            prevKey = null,
            nextKey = nextPageNumber + 1
        )
        // making the nextKey as nextPageNumber makes this go infinite
    }

}