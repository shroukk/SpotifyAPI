# Spotify API Tests
Testing Spotify APIs using Rest-assured framework based on Spotify documentation https://developer.spotify.com/documentation/web-api

## Overview
This project demonstrates the testing of Spotify APIs using the Rest-assured framework. The focus is on positive and negative scenarios, covering various endpoints defined in the Spotify API documentation.

# Tested Features:
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
- 
### Renew tokens
- Renew tokens with valid & invalid data

## Test Cases
- Check status code.
- Validate returned values in the response body.
- Ensure proper handling of errors.
  
## Prerequisites
- JDK 21
- Maven
- InteliJ
- A spotify account.
- request an access token by implementing Authorization code flow
  
## Applied Technologies
- Rest-assured framework
- TestNG
- Lombok lib
- Allure report
- Data driven
  
## Installation 
- clone the git repository.
- run command line "mvn clean test".

