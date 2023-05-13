# Android Movie App

This is a simple movie app that allows users to browse and watch movies, series and people from [The Movie Database (TMDb)](https://www.themoviedb.org/). Users can also create their own profiles, search for content, and access a menu with various options. The app uses Firebase for authentication and TMDb API for data.

## Features

- Entrance: Users can log in, create an account, or reset their password using Firebase Authentication.
- Main: Users can see a tab bar with three tabs: Movies, Series and People. Each tab shows a list of popular items from TMDb API. Users can also access a menu with options such as Profile, Settings, About and Logout. Users can also search for movies, series or people using the search bar.
- Settings: Users can update their profile information, change their password or email using Firebase Authentication.
- Details: Users can tap on any item to see more details, such as overview, genres, cast, crew, and trailers. Users can also watch the trailers using YouTube Player API.

## Screenshots
![Login](https://drive.google.com/uc?export=view&id=1LOorzCNJRSoM3Vb0LtjtgTlkugg-ZuwG "Login")
![Main](https://drive.google.com/uc?export=view&id=1L5PGTrv-29ld4qWAzNm15N8sDNyGZaGN "Main")
![Movie](https://drive.google.com/uc?export=view&id=1W8qBH8aRpFUwa2uQHm6md1sUdjXctFPM "Movie")
![Settings](https://drive.google.com/uc?export=view&id=1scjM53XynTCrk48ErxChGIUUWDyP1ueo "Settings")
## Installation

To run this app, you need to have Android Studio installed on your computer. You also need to create a Firebase project and a TMDb account to get the API keys. Follow these steps to set up the app:

- Clone this repository to your local machine using `git clone https://github.com/congdanh2504/MovieApp`.
- Open the project in Android Studio and wait for the Gradle sync to finish.
- Go to [Firebase Console](https://console.firebase.google.com/) and create a new project. Follow the instructions to add Firebase to your app and download the `google-services.json` file. Place the file in the `app` folder of your project.
- Go to [TMDb website](https://www.themoviedb.org/) and create an account. Go to [Settings](https://www.themoviedb.org/settings/api) and request an API key. Copy the key and paste it in the `gradle.properties` file of your project as `api_key="[your-key]"`.
- Run the app on an emulator or a real device and enjoy!

## Libraries

This app uses the following libraries:

- [Firebase Authentication](https://firebase.google.com/docs/auth) - for user authentication
- [Retrofit](https://square.github.io/retrofit/) - for network requests
- [Gson](https://github.com/google/gson) - for JSON parsing
- [Coil](https://coil-kt.github.io/coil/) - for image loading
- [Android Youtube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player) - for playing trailers
- [Material Components](https://material.io/develop/android) - for UI design
