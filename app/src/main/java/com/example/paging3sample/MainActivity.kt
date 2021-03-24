package com.example.paging3sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3sample.adapters.FooterAdapter
import com.example.paging3sample.adapters.ReopAdapter
import com.example.paging3sample.viewModels.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val recyclerAdapter = ReopAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        recyclerView.apply {
            adapter = recyclerAdapter.withLoadStateFooter(FooterAdapter { recyclerAdapter.retry() })
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.getPagingData().collect { pagingData ->
                recyclerAdapter.submitData(pagingData)
            }

            recyclerAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    }
                    is LoadState.NotLoading -> {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    is LoadState.Error -> {
                        val state = it.refresh as LoadState.Error
                        progressBar.visibility = View.INVISIBLE
                        //Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show
                    }
                }
            }
        }
    }
}