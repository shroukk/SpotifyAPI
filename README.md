# Spotify API Tests
Testing Spotify APIs using Rest-assured library based on Spotify documentation https://developer.spotify.com/documentation/web-api

## Overview
This project demonstrates the testing of Spotify APIs using the Rest-assured framework. The focus is on positive and negative scenarios, covering various endpoints defined in the Spotify API documentation.

# Testable Features:
### Playlist
- Playlist creation, retrieval, and updating.
- Retrieving user playlists.
- Adding and removing tracks from a playlist.
- applied positive and negative scenarios.
  
### User Profile
- Get a user profile data.
- Follow & Unfollow a playlist.
- Follow & Unfollow artists.
- applied positive and negative scenarios.
  
### Renew tokens
- Renew tokens with valid & invalid data

## Test Cases
- Check status code.
- Validate returned values in the response body.
- Ensure proper handling of errors.
  
### Allure Report
[SpotifyAPI Testcases Report](https://shroukk.github.io/SpotifyAPI/index.html)

## Prerequisites
- JDK 21
- Maven
- InteliJ
- A spotify account.
- request an access token by implementing Authorization code flow
  
## Applied Technologies
- Rest-assured.
- TestNG.
- Lombok lib.
- Allure report.
- Data driven with using json and properties files.
- POJO classes.
- assertions using hamcrest lib.
  
## Installation 
- clone the git repository.
- run command line "mvn clean test".
- to generate report "mvn allure:report"

