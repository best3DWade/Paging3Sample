package com.example.paging3sample.entities

import com.google.gson.annotations.SerializedName

class RepoResponse {
    @SerializedName("items") val items : List<Reop> = emptyList()
}