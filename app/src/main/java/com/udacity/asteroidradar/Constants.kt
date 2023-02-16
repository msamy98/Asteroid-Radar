package com.udacity.asteroidradar

import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = BuildConfig.API_KEY

    fun getTodayDate(): String {
        val calender = Calendar.getInstance()
        val today = calender.time
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
        } else {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        }
        return formatter.format(today)
    }

    fun getAfterSevenDaysDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_END_DATE_DAYS)
        val afterWeekDate = calendar.time
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
        } else {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        }
        return formatter.format(afterWeekDate)
    }
}

