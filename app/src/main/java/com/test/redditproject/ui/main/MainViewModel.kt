package com.test.redditproject.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.redditproject.api.ApiRepository
import com.test.redditproject.api.ChildrenModel
import com.test.redditproject.api.ParentRequestModel
import com.test.redditproject.api.Result.Error
import com.test.redditproject.api.Result.Success
import com.test.redditproject.data.PostsRepository
import com.test.redditproject.data.mapModelToInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val api = ApiRepository.getInstance()
    private val repo = PostsRepository.getInstance()


    private var lastPage = ""

    init {
        getTop()
    }

    fun getTop() {

        scope.launch {
            val res = if (lastPage.isNullOrEmpty()) {
                api.getTop()
            } else {
                api.getNext(lastPage, 10.toString())
            }

            when (res) {
                is Success -> {
                    Log.d("request result", res.data.toString())
                    val model = res.data as ParentRequestModel

                    saveToDb(model.data.children, repo!!)
                }
                is Error -> {
                    Log.e("request error", res.errMsg)

                }
            }
        }
    }

    private fun saveToDb(listPosts: List<ChildrenModel>, repo: PostsRepository) {
        listPosts.mapNotNull { it.data }
            .filter { (it.id != null) }
            .forEach { repo.savePost(it.mapModelToInfo()) }
    }
}