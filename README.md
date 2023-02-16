# Asteroid Radar Application (Using open-source NASA API)

If you are an asteroids enthusiast, well this application is for you.

## Project Overview

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids given a period of time with data such as the size, velocity, distance to earth and if they are potentially hazardous. In this project, you will apply the skills such as fetching data from the internet, saving data to a database, and display the data in a clear, compelling UI.

You will need the NEoWs API which is a free, open source API provided by NASA JPL Asteroid team, as they explain it: “Is a RESTful web service for near earth Asteroid information. With NeoWs a user can: search for Asteroids based on their closest approach date to Earth, lookup a specific Asteroid with its NASA JPL small body id, as well as browse the overall data-set.”

## Features:

- Main screen to show list of clickable asteroids code names along with close-to-earth date.
- Details screen displays all the data of the choosen asteroid such as the size, velocity, distance to earth and if they are potentially hazardous.
- Downloads the data from the NASA API (NeoWS) and parse it.
- The application caches all the coming week data using [WorkManager](https://developer.android.com/guide/background/persistent) in the background only when the mobile is charging, connected to wifi and idel.
- The application also provide excellent talk back experience

## Using NASA API:
- First you need to get your own API key which is provided by [NASA API](https://api.nasa.gov/).
- Then add your API key in the local.proerties file as apiKey = "Your API Key"

## Dependencies

The most important dependencies used are:
- Retrofit to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in form of custom classes.
- Picasso to download and cache images.
- RecyclerView to display the asteroids in a list.

The following components from the Jetpack library are used:
- ViewModel
- Room
- LiveData
- Data Binding
- Navigation

## Built With

* [Android Studio](https://developer.android.com/studio) - Default IDE used to build android apps
* [Kotlin](https://kotlinlang.org/) - Default language used to build this project
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Jetpack's Navigation component, used for Fragment-based navigation 
* [Retrofit](https://github.com/square/retrofit) - a type-safe HTTP client for Android and Java
* [Moshi](https://github.com/square/moshi) - a modern JSON library for Android and Java, that makes it easy to parse JSON into Java or Kotlin objects
* [Picasso](https://square.github.io/picasso/) - A powerful image downloading and caching library for Android
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - a collection of libraries that help design robust, testable, and maintainable apps: Room (a SQLite object mapping library), LiveData (builds data objects that notify views when the underlying database changes), ViewModel (stores UI-related data that isn't destroyed on app rotations)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding) - a Jetpack support library that allows  to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* [MVVM](https://developer.android.com/jetpack/guide) - the architecture pattern 

## Application Screenshots

![main_screen_Asteroid Radar](https://user-images.githubusercontent.com/50630616/219385891-eb80506d-df4a-48ac-82fc-dfdcc34504da.jpg)
![main_screen_filtering_Asteroid Radar](https://user-images.githubusercontent.com/50630616/219385953-696f0899-300b-4b97-947b-1bb0a82eaeb4.jpg)
![potentially_hazard_asteroid_details_Asteroid Radar](https://user-images.githubusercontent.com/50630616/219385980-1e8a8b60-d8cd-427e-bc0c-88caf1888b4e.jpg)
![safe_asteroid_details_Asteroid Radar](https://user-images.githubusercontent.com/50630616/219386003-a50723f6-896d-4c06-b952-ca599f2f8668.jpg)
![unit_info_Asteroid Radar](https://user-images.githubusercontent.com/50630616/219386029-1be8615f-8ed1-4363-a4d5-fd2d7ff266da.jpg)

## Udacity Code Review

![image](https://user-images.githubusercontent.com/50630616/219391057-5d98de30-412e-4abc-8411-e60e41d830e1.png)
![image](https://user-images.githubusercontent.com/50630616/219387748-73f0ba41-d57e-4b36-88b3-df424bb388b4.png)
![image](https://user-images.githubusercontent.com/50630616/219388080-b200a50a-a6b2-47a2-a209-7f3ef34c1d3a.png)
![image](https://user-images.githubusercontent.com/50630616/219388340-ccb5983a-e1f6-4c2b-8bfc-08219650ce19.png)
![image](https://user-images.githubusercontent.com/50630616/219388627-f6bb7248-43fc-4f61-b5a5-14d44d5560da.png)
![image](https://user-images.githubusercontent.com/50630616/219388953-32de3390-acf9-48a8-875e-c91aee1435f1.png)
![image](https://user-images.githubusercontent.com/50630616/219388699-a8157721-2d05-4f66-b417-961f9e399621.png)
![image](https://user-images.githubusercontent.com/50630616/219389355-0b3f5f75-1626-460e-870d-6b8bf69172e6.png)
![image](https://user-images.githubusercontent.com/50630616/219389455-8a9dee37-8397-4805-8919-910ff94e6de2.png)
![image](https://user-images.githubusercontent.com/50630616/219389976-bb8eab32-4b93-43ea-a652-1ebe94de76d2.png)
![image](https://user-images.githubusercontent.com/50630616/219390148-a910c5b9-bdea-4a28-95b1-1e3381d5dcda.png)
![image](https://user-images.githubusercontent.com/50630616/219390357-a1bc50c1-5a40-4b4c-b52d-adecc4772d49.png)
![image](https://user-images.githubusercontent.com/50630616/219390659-09dbda5a-8dae-48e3-84e6-d8b4ca0f491a.png)


