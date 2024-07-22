package com.example.myfirstapp.todo.data.remote

import com.example.myfirstapp.todo.data.Series
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ItemService {
    @GET("/api/series")
    suspend fun find(@Header("Authorization") authorization: String): List<Series>

    @GET("/api/series/{id}")
    suspend fun read( @Header("Authorization") authorization: String, @Path("id") itemId: String?): Series;

    @Headers("Content-Type: application/json")
    @POST("/api/series")
    suspend fun create(@Header("Authorization") authorization: String, @Body series: Series): Series

    @Headers("Content-Type: application/json")
    @PUT("/api/series/{id}")
    suspend fun update( @Header("Authorization") authorization: String, @Path("id") itemId: String?, @Body series: Series): Series
}
