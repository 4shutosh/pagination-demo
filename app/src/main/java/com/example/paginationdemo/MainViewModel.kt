package com.example.paginationdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel constructor(

) : ViewModel() {


    data class ListViewState(
        val id: Int,
        val title: String,
    )

    val demoListItems = Pager(config = PagingConfig(
        30, 2
    ), pagingSourceFactory = { DemoPagingSource() }).flow.cachedIn(viewModelScope)

}