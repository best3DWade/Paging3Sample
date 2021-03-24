package com.example.paging3sample.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3sample.entities.Reop
import com.example.paging3sample.repositories.Repository
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    fun getPagingData(): Flow<PagingData<Reop>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}