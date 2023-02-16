package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repos.AsteroidRepo
import com.udacity.asteroidradar.repos.PictureOfDayRepo
import retrofit2.HttpException

class RefreshDataWork(params: WorkerParameters, context: Context):CoroutineWorker(context,params) {
    companion object{
        const val WORK_NAME = "RefreshDataWork"
        const val ONETIME_WORK_NAME = "OneTimeRefreshDataWork"
    }

    override suspend fun doWork(): Result {
        val database = AsteroidsDatabase.getInstance(applicationContext)
        val asteroidRepo = AsteroidRepo(database)
        val pictureOfDayRepo = PictureOfDayRepo(database)
        return try {
            asteroidRepo.refreshDatabase()
            pictureOfDayRepo.updatePictureOfDay()
            Result.success()
        }catch (ex: HttpException){
            Result.retry()
        }
    }

}