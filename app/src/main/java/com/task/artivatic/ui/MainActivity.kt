package com.task.artivatic.ui

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.task.artivatic.MyApp
import com.task.artivatic.R
import com.task.artivatic.databinding.ActivityMainBinding
import com.task.artivatic.pojo.ExampleData
import com.task.artivatic.ui.adapter.ListDataAdapter
import com.task.artivatic.listener.EventListener
import com.task.artivatic.utils.Utils.isNetworkAvailable
import com.task.artivatic.viewmodels.MainViewModel
import com.task.artivatic.viewmodels.MainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var listDataAdapter: ListDataAdapter
    var dataList = ArrayList<ExampleData.Row>()

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as MyApp).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        setObserver()
        initView()
    }

    private fun initView() = with(binding) {
        setListViewAdapter()
        pullToRefresh.setOnRefreshListener {
            callApi()
        }
        callApi()
    }

    private fun setListViewAdapter() = with(binding) {
        listDataAdapter =
            ListDataAdapter(
                this@MainActivity,
                dataList,
                object : EventListener<ExampleData.Row> {
                    override fun onItemClick(pos: Int, item: ExampleData.Row, view: View) {
                    }
                })
        listView.adapter = listDataAdapter
        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                listView: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                val topRowVerticalPosition =
                    if (listView == null || listView.childCount === 0) 0 else binding.listView.getChildAt(
                        0
                    ).top
                pullToRefresh.isEnabled = topRowVerticalPosition >= 0
            }
        })

    }


    private fun callApi() = with(binding) {
        if (isNetworkAvailable(this@MainActivity)) {
            pullToRefresh.isRefreshing = true
            mainViewModel.callApi()
        } else {
            pullToRefresh.isRefreshing = false
            showToast("Please connect to internet.")
        }
    }

    private fun setObserver() = with(binding) {
        mainViewModel.apiResponseLiveData.observe(this@MainActivity, Observer {
            pullToRefresh.isRefreshing = false
            if (it != null) {
                supportActionBar?.title = it.title
                if (it.rows.isNullOrEmpty().not()) {
                    dataList.clear()
                    it.rows?.let { it1 -> dataList.addAll(it1) }
                    listDataAdapter.notifyDataSetChanged()
                }
            } else {
                showToast("something went wrong")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

}



















