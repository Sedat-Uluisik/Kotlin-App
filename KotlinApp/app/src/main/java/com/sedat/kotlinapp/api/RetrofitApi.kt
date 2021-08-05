package com.sedat.kotlinapp.api

import com.sedat.kotlinapp.model.RetrofitImageResponse
import com.sedat.kotlinapp.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    //pixabay.com sitesinden istek yapıldı.

    @GET("/api/")
    suspend fun getImages(
        @Query("per_page") per_page: Int = 20,
        @Query("key") api_key: String = API_KEY
    ): Response<RetrofitImageResponse>

}