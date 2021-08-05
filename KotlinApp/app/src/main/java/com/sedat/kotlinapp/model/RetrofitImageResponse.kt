package com.sedat.kotlinapp.model

data class RetrofitImageResponse(
    val hits: List<RetrofitImageResult>,
    val total: Int,
    val totalHits: Int
)