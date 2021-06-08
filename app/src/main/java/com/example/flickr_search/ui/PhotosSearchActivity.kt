package com.example.flickr_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickr_search.R
import kotlinx.android.synthetic.main.activity_photos_search.photosRecyclerView
import kotlinx.android.synthetic.main.activity_photos_search.searchBox
import androidx.core.widget.addTextChangedListener

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SEARCH_DELAY_MS = 200L

class PhotosSearchActivity : AppCompatActivity() {
    private val photosSearchViewModel : PhotoSearchViewModel by viewModels()

    private val photosAdapter = PhotosSearchAdapter()
    private var searchJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_search)

        searchBox.addTextChangedListener { editable ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(SEARCH_DELAY_MS)
                val imagesList = photosSearchViewModel.images(editable.toString())
                with(photosAdapter) {
                    photos.clear()
                    photos.addAll(imagesList)
                    notifyDataSetChanged()
                }
            }
        }
        // Set up recycler view
        photosRecyclerView.adapter = photosAdapter
        photosRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }
}