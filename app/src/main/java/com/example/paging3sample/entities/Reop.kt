package com.example.paging3sample.entities

import com.google.gson.annotations.SerializedName

data class Reop (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String?,
    @SerializedName("stargazers_count") val starCounts : String
)