package com.example.paginationdemo

object PseudoDataSource {

    fun getPseudoDataSourceListViewState(
        page: Int,
        pageLimitSize: Int = 29,
    ): MutableList<MainViewModel.ListViewState> {
        val pageList = mutableListOf<MainViewModel.ListViewState>()

        for (i in 0..pageLimitSize) {
            pageList.add(MainViewModel.ListViewState(
                id = "$page$i".toInt(),
                title = "Title page $page index $i"
            ))
        }

        return pageList
    }
}