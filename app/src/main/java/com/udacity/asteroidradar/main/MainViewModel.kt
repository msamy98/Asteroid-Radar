package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.repos.AsteroidRepo
import com.udacity.asteroidradar.repos.PictureOfDayRepo
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val asteroidRepository: AsteroidRepo, private val pictureOfDayRepo: PictureOfDayRepo) : ViewModel() {

    val asteroids:MediatorLiveData<List<Asteroid>> = MediatorLiveData()
    private val todayAsteroids = asteroidRepository.todayAsteroids
    private val weekAsteroids = asteroidRepository.weekAsteroids
    private val allSavedAsteroids = asteroidRepository.allAsteroids
    val pictureOfDay = pictureOfDayRepo.pictureOfDay

    private val _onRefreshDatabaseFailed = MutableLiveData<Boolean>()
    val onRefreshDatabaseFailed: LiveData<Boolean>
    get() = _onRefreshDatabaseFailed

    private val _eventNavigateToAsteroidDetailFragment = MutableLiveData<Asteroid?>()
    val eventNavigateToAsteroidDetailFragment: LiveData<Asteroid?>
    get() = _eventNavigateToAsteroidDetailFragment

    fun onRefreshDatabaseFailureHandled(){
        _onRefreshDatabaseFailed.value = false
    }




    init{
        _eventNavigateToAsteroidDetailFragment.value = null
        _onRefreshDatabaseFailed.value = false
        addSourceToMediatorLiveDataList(weekAsteroids)
        viewModelScope.launch {
            try {
                asteroidRepository.refreshDatabase()
                pictureOfDayRepo.updatePictureOfDay()
            }catch (ex: IOException){
                _onRefreshDatabaseFailed.value = true
            }
        }
    }

    fun onTodayAsteroidsClicked(){
        removeSource()
        addSourceToMediatorLiveDataList(todayAsteroids)
    }

    fun onWeekAsteroidsClicked(){
        removeSource()
        addSourceToMediatorLiveDataList(weekAsteroids)
    }

    fun onSavedAsteroidsClicked(){
        removeSource()
        addSourceToMediatorLiveDataList(allSavedAsteroids)
    }

    private fun addSourceToMediatorLiveDataList(asteroidsList: LiveData<List<Asteroid>>) {
        asteroids.addSource(asteroidsList) { asteroidsList ->
            asteroids.value = asteroidsList
        }
    }

    private fun removeSource() {
        asteroids.removeSource(todayAsteroids)
        asteroids.removeSource(weekAsteroids)
        asteroids.removeSource(allSavedAsteroids)
    }

    fun navigateToAsteroidDetailsFragment(asteroid: Asteroid) {
        _eventNavigateToAsteroidDetailFragment.value = asteroid
    }

    fun onNavigatedToAsteroidDetailFragment(){
        _eventNavigateToAsteroidDetailFragment.value = null
    }

    class Factory(private val asteroidRepository: AsteroidRepo, private val pictureOfDayRepo: PictureOfDayRepo):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(asteroidRepository,pictureOfDayRepo) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }

}