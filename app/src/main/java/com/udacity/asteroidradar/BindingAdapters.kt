package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.recyclerViewAdapter.AsteroidsRecyclerViewAdapter

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription =
            imageView.context.getString(R.string.potentially_hazardous_asteroid)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = imageView.context.getString(R.string.safe_asteroid)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription =
            imageView.context.getString(R.string.potentially_hazardous_asteroid)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = imageView.context.getString(R.string.safe_asteroid)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    (recyclerView.adapter as AsteroidsRecyclerViewAdapter).submitList(data)
}

@BindingAdapter("setVisibility")
fun setViewVisibility(view: View, it: MediatorLiveData<List<Asteroid>>?) {
    view.visibility = if (it?.value?.isEmpty() == false) View.GONE else View.VISIBLE
}

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    pictureOfDay?.let { picture ->
        if (picture.mediaType == "image") {
            val imageUri = picture.url.toUri().buildUpon().scheme("https").build()
            Picasso.with(imageView.context)
                .load(imageUri)
                .placeholder(R.drawable.placeholder_picture_of_day)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.instead_of_picture_of_the_day)
            imageView.contentDescription = imageView.context.getString(R.string.instead_of_picture_of_day)
        }
    }
}
