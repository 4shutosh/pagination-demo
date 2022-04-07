package com.example.paginationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    private val pagingAdapter = DemoPagingAdapter()
    private val listAdapter = DemoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        binding.rvList.apply {
            adapter = listAdapter
        }
        binding.rvList.addOnScrollListener(
            object : ManualPagingScrollListener(PAGE_SIZE) {
                override fun loadMoreData(page: Int) {
                    Log.d("TAG", "loadMoreData: $page")
                    viewModel.actionLoadMoreData(page)
                }

            }
        )
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.demoListItemsPaging.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        viewModel.demoListItemManualPaging.observe(this) {
            Log.d("TAG", "setUpObservers: ${it.size}")
            listAdapter.submitList(ArrayList(it))
        }
    }

    companion object {
        const val PAGE_SIZE = 30
    }

}