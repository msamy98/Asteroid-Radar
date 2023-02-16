package com.udacity.asteroidradar

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "picture_of_the_day")
data class PictureOfDay(
    @Json(name = "media_type") val mediaType: String, val title: String,
    @PrimaryKey
    val url: String
){
    var dateOfPicture = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().time)
}