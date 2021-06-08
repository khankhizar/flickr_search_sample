package com.example.flickr_search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr_search.R
import com.example.flickr_search.domain.SearchedPhoto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_gallery.view.*

class PhotosSearchAdapter(val photos: MutableList<SearchedPhoto> = mutableListOf()) : RecyclerView.Adapter<PhotosSearchAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.search_gallery,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: SearchedPhoto) {
            Picasso.get().
            load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(itemView.ivPhotos)
        }
    }
}

const val IMAGE_SIDE_PX = 400