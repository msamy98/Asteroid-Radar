package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidsDao {
    @Query("select * from asteroids ORDER BY date(closeApproachDate) ASC")
    fun getAllAsteroids(): LiveData<List<Asteroid>>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate >= :date ORDER BY date(closeApproachDate) ASC")
    fun getAsteroidsFromToday(date: String): LiveData<List<Asteroid>>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate = :date ORDER BY date(closeApproachDate) ASC")
    fun getTodayAsteroids(date: String): LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateAsteroids(asteroids: List<Asteroid>)
}