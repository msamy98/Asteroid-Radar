package com.udacity.asteroidradar.repos

import android.os.Build
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRepo(private val database: AsteroidsDatabase) {

    suspend fun refreshDatabase(){
        withContext(Dispatchers.IO){
            val asteroids = NasaApi.retrofitService.getAsteroids(Constants.API_KEY,Constants.getTodayDate(),Constants.getAfterSevenDaysDate())
            val jsonAsteroids = JSONObject(asteroids)
            val parsedAsteroids = parseAsteroidsJsonResult(jsonAsteroids)
            database.asteroidDao.updateAsteroids(parsedAsteroids)
        }
    }

    val weekAsteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroidsFromToday(Constants.getTodayDate())
    val todayAsteroids: LiveData<List<Asteroid>> = database.asteroidDao.getTodayAsteroids(Constants.getTodayDate())
    val allAsteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAllAsteroids()





}















