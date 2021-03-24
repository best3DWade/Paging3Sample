package com.example.paging3sample.services

import com.example.paging3sample.entities.RepoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories?sort=stars&q=Android")
    suspend fun getResponses(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): RepoResponse

    companion object{
        private const val BASE_URL = "https://api.github.com/"
        fun create() : GithubService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }
}