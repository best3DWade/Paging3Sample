package com.example.paging3sample.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3sample.entities.Reop

class ReopPagingResource(private val githubService: GithubService) : PagingSource<Int, Reop>() {
    override fun getRefreshKey(state: PagingState<Int, Reop>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Reop> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = githubService.getResponses(page, pageSize)
            val reopItems = repoResponse.items
            val prevPage = if (page > 1) page - 1 else null
            val nextPage = if (reopItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(reopItems, prevPage, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}