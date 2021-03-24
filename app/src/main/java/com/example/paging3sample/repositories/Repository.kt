package com.example.paging3sample.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3sample.entities.Reop
import com.example.paging3sample.services.GithubService
import com.example.paging3sample.services.ReopPagingResource
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 50

    private val githubService = GithubService.create()

    fun getPagingData(): Flow<PagingData<Reop>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { ReopPagingResource(githubService) }
        ).flow
    }

}