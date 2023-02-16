package com.udacity.asteroidradar.repos

import android.util.Log
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.database.AsteroidsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureOfDayRepo(private val database: AsteroidsDatabase) {

    val pictureOfDay = database.pictureOfTheDayDao.getTodayPicture()

    suspend fun updatePictureOfDay() {
        withContext(Dispatchers.IO) {
            val pictureOfDay = NasaApi.retrofitService.getPictureOfTheDay(Constants.API_KEY)
            database.pictureOfTheDayDao.insertPicture(pictureOfDay)
        }
    }
}