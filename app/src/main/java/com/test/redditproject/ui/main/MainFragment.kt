package com.test.redditproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.redditproject.R
import com.test.redditproject.util.PaginationScrollListener
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    val layoutManager by lazy { LinearLayoutManager(activity) }
    var isLoading: Boolean = false

    lateinit var adapter: MainAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMain.layoutManager = layoutManager
        adapter = MainAdapter()
        rvMain.adapter = adapter

        rvMain.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
//                We assume that Reddit has infinite count of data
                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                viewModel.getTop()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.topList.observe(viewLifecycleOwner, Observer { top ->
            adapter.addData(top)
        })
        viewModel.isLoadingLD.observe(viewLifecycleOwner, Observer { isLoading = it })
    }

}