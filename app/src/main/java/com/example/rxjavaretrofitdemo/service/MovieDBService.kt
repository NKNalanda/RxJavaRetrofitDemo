package com.example.rxjavaretrofitdemo.service

import com.example.rxjavaretrofitdemo.model.MovieDBResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieDBResponse>
}