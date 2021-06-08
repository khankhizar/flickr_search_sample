package com.example.flickr_search.ui

import androidx.lifecycle.ViewModel
import com.example.flickr_search.domain.SearchedPhoto
import com.example.flickr_search.networking.WebClient

class PhotoSearchViewModel : ViewModel() {
    suspend fun images(search: String): List<SearchedPhoto> {
        if (search.isBlank()){
            return emptyList()
        }
        val searchResponse = WebClient.client.fetchImages(search)
        return searchResponse.photos.photo.map { photo ->
            SearchedPhoto(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }
    }
}