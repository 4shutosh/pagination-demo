package com.example.paginationdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paginationdemo.PseudoDataSource.getPseudoDataSourceListViewState

class MainViewModel constructor(

) : ViewModel() {


    data class ListViewState(
        val id: Int,
        val title: String,
    )

    val demoListItemsPaging = Pager(config = PagingConfig(
        30, 2
    ), pagingSourceFactory = { DemoPagingSource() }).flow.cachedIn(viewModelScope)

    private val _demoListItemManualPaging =
        MutableLiveData<MutableList<ListViewState>>(mutableListOf())
    val demoListItemManualPaging: LiveData<MutableList<ListViewState>> = _demoListItemManualPaging

    init {
        actionLoadMoreData(1)
    }


    fun actionLoadMoreData(page: Int) {
        val list = _demoListItemManualPaging.value!!
        list.addAll(getPseudoDataSourceListViewState(page))
        _demoListItemManualPaging.postValue(list)
    }

}