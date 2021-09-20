package com.example.android.photos.util

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.android.photos.R
import com.example.android.photos.photos.Photo
import com.example.android.photos.photos.PhotosAdapter


@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Photo>?) {
    items?.let {
        (listView.adapter as PhotosAdapter).submitList(it)
    }
}

@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val glideUrl = GlideUrl(it, LazyHeaders.Builder()
            .addHeader("User-Agent", "your-user-agent")
            .build())

        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(imageView.context)
            .load(glideUrl)
            .apply(
                RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("title")
fun setText(textView: TextView, title: String?) {
    title?.let {
        textView.text = textView.context.getString(R.string.title, it)
    }
}

@BindingAdapter("listVisibility")
fun setListVisibility(listView: RecyclerView, status: PhotoApiStatus?) {
    status?.let {
        when (it) {
            is PhotoApiStatus.Loading -> {
                listView.visibility = RecyclerView.GONE
            }
            else -> {
                listView.visibility = RecyclerView.VISIBLE
            }
        }
    }
}

@BindingAdapter("progressBarVisibility")
fun setProgressVisibility(progressBar: ProgressBar, status: PhotoApiStatus?) {
    status?.let {
        when (it) {
            is PhotoApiStatus.Loading -> {
                progressBar.visibility = ProgressBar.VISIBLE
            }
            else -> {
                progressBar.visibility = ProgressBar.GONE
            }
        }
    }
}