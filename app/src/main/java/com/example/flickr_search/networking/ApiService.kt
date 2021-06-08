package com.example.flickr_search.networking

import com.example.flickr_search.data.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=1a013b668241fc992b74736e2e4ae9bc")
    suspend fun fetchImages(@Query(value = "text") search: String): PhotosSearchResponse
}