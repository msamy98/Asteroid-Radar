package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repos.AsteroidRepo
import com.udacity.asteroidradar.repos.PictureOfDayRepo
import com.udacity.asteroidradar.work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidsApplication : Application() {

    val database: AsteroidsDatabase by lazy {
        AsteroidsDatabase.getInstance(applicationContext)
    }
    val asteroidsRepository: AsteroidRepo by lazy {
        AsteroidRepo(database)
    }
    val pictureOfDayRepository: PictureOfDayRepo by lazy {
        PictureOfDayRepo(database)
    }


    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setUpRecurringWork()
        }
    }

    private fun setUpRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()


        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
//        val oneTimeConstraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.UNMETERED)
//            .build()
//
//        val oneTimeRequest = OneTimeWorkRequestBuilder<RefreshDataWork>()
//            .setConstraints(oneTimeConstraints)
//            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 3, TimeUnit.SECONDS)
//            .build()
//
//        WorkManager.getInstance().enqueueUniqueWork(
//            RefreshDataWork.ONETIME_WORK_NAME,
//            ExistingWorkPolicy.KEEP,
//            oneTimeRequest
//        )
    }
}