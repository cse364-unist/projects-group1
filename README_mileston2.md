# Description of Features

## Party Room Feature

- **Feature Description**: The Party Room feature allows users to access functionalities related to hosting virtual movie parties. It includes endpoints for managing movie lists, creating/deleting content, and providing detailed movie information. Here, "content" means additional content related to a movie made by a user.

<img width="998" alt="스크린샷 2024-05-01 오전 12 58 44" src="https://github.com/cse364-unist/projects-group1/assets/99570746/95f7d03c-1f88-4a45-9525-195bff7ea737">

This backend serves a party room application where users can browse and interact with various multimedia contents. At localhost:8080/partyroom, users can access different movies and other content types. Each movie and content item is accessible via unique endpoints that support operations like POST for content generation and DELETE for content removal. Additional features include live chat functionalities integrated via WebSockets at dynamic endpoints depending on the movie or content being accessed, enhancing real-time user interaction. For external access, media information is linked through imageurl.com with specific endpoints for each content.

- **REST APIs**:
  - `GET /partyroom/movies`: Retrieves a list of randomly selected movies by genre.
  - `POST /partyroom/contents`: Creates new content for the party room.
  - `GET /partyroom/contents`: Retrieves all available content for the party room.
  - `DELETE /partyroom/contents/{contentName}`: Deletes content from the party room by its name.
  - `GET /partyroom/movies/{movieId}`: Retrieves details about a specific movie, including its name, streaming video URL, and chat URL.
  - `GET /partyroom/contents/{contentName}`: Retrieves details about specific content in the party room, including its name, streaming video URL, and chat URL.

## Location-Based Movie Spot Recommendation Feature

- **Feature Description**: The Location-Based Movie Spot Recommendation feature provides functionalities related to movie filming locations. It allows fetching information about specific movie filming locations, recommending nearby filming locations based on the user's location, and retrieving lists of all available filming locations.
- **REST APIs**:
  - `GET /places/{movieId}`: Retrieves information about a specific movie filming location.
  - `GET /places/recommends/distance/{threshold}`: Recommends nearby filming locations based on the user's location.
  - `GET /places`: Retrieves information about a specific filming location.
  - `GET /places/all`: Retrieves lists of all available filming locations.
  - `GET /places/all/{placeId}`: Retrieves a list of movies filmed at a specific filming location.
  - `GET /places/recommends/{userId}`: Retrieves the top 5 recommended movie filming locations based on the user's location.

## Point System Feature

- **Feature Description**: The Point System feature incentivizes user interaction and participation by rewarding points for activities like quizzes and reviews. It includes endpoints for managing user points.
- **REST APIs**:
  - `GET /quizzes/{quizId}`: Retrieves quiz details by quiz ID.
  - `POST /quizzes/{quizId}`: Checks the user's answer for a quiz and returns the result.
  - `POST /quizzes/reset/{quizId}`: Resets the status of the quiz for a specific user.
  - `GET /users/{userId}`: Retrieves user details by user ID.

# Example cURL Commands & Expected Outputs
## Party Room Feature
- `GET /partyroom/movies`
  - **Description**: Retrieves a list of randomly selected movies by distinic genre.
  - **cURL Command**: `curl -X GET http://localhost:8080/partyroom/movies`
  - **Expected Output**:
    ```json
    [
      {
        "id": "662e4b7a01eddf58f8e49c41",
        "movieId": 2974,
        "name": "Bats (1999)",
        "genre": "Horror|Thriller",
        "url": "http://localhost:8080/movies/2974",
        "placeId": 184
      },
      {
        "id": "662e4b7a01eddf58f8e49f5a",
        "movieId": 3767,
        "name": "Missing in Action 2: The Beginning (1985)",
        "genre": "Action|War",
        "url": "http://localhost:8080/movies/3767",
        "placeId": 113
      },
      {
        "id": "662e4b7a01eddf58f8e49384",
        "movieId": 674,
        "name": "Barbarella (1968)",
        "genre": "Adventure|Sci-Fi",
        "url": "http://localhost:8080/movies/674",
        "placeId": 61
      }
    ]
    ```
- `POST /partyroom/contents`
  - **Description**: Creates new content for the party room.
  - **cURL Command**: `curl -X POST http://localhost:8080/partyroom/contents -d "{\"contentName\": \"newContent\"}" -H "Content-Type: application/json"`
  - **cURL Command**: `curl -X POST http://localhost:8080/partyroom/contents -d "{\"contentName\": \"newContent2\"}" -H "Content-Type: application/json"`
  - **Expected Output**:
    ```json
    {
      "id":"662e5183b8993c2b4e63fc99",
      "name":"newContent",
      "url":"http://localhost:8080/contents/newContent"
    },
    {
      "id":"6631e11f89480e7bad92563b",
      "name":"newContent2",
      "url":"http://localhost:8080/contents/newContent2"
    }
    ```
- `GET /partyroom/contents`
  - **Description**: Retrieves all available content for the party room.
  - **cURL Command**: `curl -X GET http://localhost:8080/partyroom/contents`
  - **Expected Output**:
    ```json
    [
      {
        "id":"662e5183b8993c2b4e63fc99",
        "name":"newContent",
        "url":"http://localhost:8080/contents/newContent"
      },
      {
        "id":"6631e11f89480e7bad92563b",
        "name":"newContent2",
        "url":"http://localhost:8080/contents/newContent2"
      },
      ...
    ]
    ```
- `DELETE /partyroom/contents/{contentName}`
  - **Description**: Deletes content from the party room by its name.
  - **cURL Command**: `curl -X DELETE http://localhost:8080/partyroom/contents/newContent`
  - **Expected Output**: `Content 'newContent' deleted successfully.`
- `GET /partyroom/movies/{movieId}`
  - **Description**: Retrieves details about a specific movie, including its name, streaming video URL, and chat URL.
  - **cURL Command**: `curl -X GET http://localhost:8080/partyroom/movies/2974`
  - **Expected Output**:
    ```json
    {
      "chatUrl": "ws://localhost:8080/partyroom/chat/2974",
      "streamVideoUrl": "http://videostreaming.com/partyroom/2974",
      "movieName": "Bats (1999)"
    }
    ```
- `GET /partyroom/contents/{contentName}`
  - **Description**: Retrieves details about specific content in the party room, including its name, streaming video URL, and chat URL.
  - **cURL Command**: `curl -X GET http://localhost:8080/partyroom/contents/newContent`
  - **Expected Output**:
  ```json
  {
    "chatUrl": "ws://localhost:8080/partyroom/chat/newContent2",
    "streamVideoUrl": "http://videostreaming.com/partyroom/newContent2",
    "contentName": "newContent2"
  }
  ```

## Location-Based Movie Spot Recommendation Feature
- `GET /places/{movieId}`
  - **Description**: Retrieves information about a specific movie filming location.
  - **cURL Command**: `curl -X GET http://localhost:8080/places/184`
  - **Expected Output**:
    ```json
    {
      "placeId": 233,
      "name": "loc233",
      "hobby": 24,
      "favoriteFood": 7
    }
    ```
- `GET /places/recommends/distance/{threshold}`
  - **Description**: Recommends nearby filming locations based on the user's location. The haversine formula is used to calculate the distance between each movie location and the user. At this time, latitude and longitude data of the user and the movie filming location are used. Returns movie locations with a distance less than the threshold.
  - **cURL Command**: `curl -X GET http://localhost:8080/places/recommends/distance/50\?userId\=1`
  - **Expected Output**:
    ```json
    [
      {
        "id": "662e4b7a01eddf58f8e49c41",
        "movieId": 2974,
        "name": "Bats (1999)",
        "genre": "Horror|Thriller",
        "url": "http://localhost:8080/movies/2974",
        "placeId": 184
      },
      {
        "id": "662e4b7a01eddf58f8e49f5a",
        "movieId": 3767,
        "name": "Missing in Action 2: The Beginning (1985)",
        "genre": "Action|War",
        "url": "http://localhost:8080/movies/3767",
        "placeId": 113
      },
      {
        "id": "662e4b7a01eddf58f8e49384",
        "movieId": 674,
        "name": "Barbarella (1968)",
        "genre": "Adventure|Sci-Fi",
        "url": "http://localhost:8080/movies/674",
        "placeId": 61
      }
    ]
    ```
- `GET /places` 
  - **Description**: Retrieves information about a specific filming location.
  - **cURL Command**: `curl -X GET http://localhost:8080/places?placeId=1`
  - **Expected Output**:
    ```json
    {
      "placeId": 1,
      "name": "loc1",
      "hobby": 28,
      "favoriteFood": 23
    }
    ```
- `GET /places/all`
  - **Description**: Retrieves lists of all available filming locations.
  - **cURL Command**: `curl -X GET http://localhost:8080/places/all`
  - **Expected Output**:
    ```json
    [
      {
        "id": "662e4b8458059d9b3b5f46bf",
        "placeId": 5,
        "name": "loc5",
        "latitude": 37.9580935472931,
        "longitude": 125.697640715773,
        "hobby": 25,
        "favoriteFood": 23
      },
      {
        "id": "662e4b8458059d9b3b5f46c0",
        "placeId": 12,
        "name": "loc12",
        "latitude": 38.53235047499,
        "longitude": 129.374480582964,
        "hobby": 24,
        "favoriteFood": 23
      },
      ...
    ]
    ```
- `GET /places/all/{placeId}`
  - **Description**: Retrieves a list of movies filmed at a specific filming location.
  - **cURL Command**: `curl -X GET http://localhost:8080/places/all/5`
  - **Expected Output**:
    ```json
    [
      {
        "name": "No Escape (1994)",
        "genre": "Action|Sci-Fi",
        "placeId": 5
      },
      {
        "name": "Charm's Incidents (1996)",
        "genre": "Drama",
        "placeId": 5
      },
      ...
    ]
    ```
- `GET /places/recommends/{userId}`
  - **Description**: Retrieves the top 5 recommended movie filming locations based on the user's location. hobby and favoriteFood are int type and express what kind of play and food the user likes through bit units. For example, 10010 (18) means having a hobby corresponding to the first bit and a hobby corresponding to the fourth bit. Therefore, the Hamming distance, which represents the distance between bits, is used to calculate the distance between the movie location's (hobby and favorite food) and the user's (hobby and favorite) food data to recommend the movie locations.
  - **cURL Command**: `curl -X GET http://localhost:8080/places/recommends/1`
  - **Expected Output**:
    ```json
    [
      {
        "id": "662e4b8458059d9b3b5f46d9",
        "placeId": 27,
        "name": "loc27",
        "latitude": 34.8019189947536,
        "longitude": 128.171284742741,
        "hobby": 2,
        "favoriteFood": 10
      },
      {
        "id": "662e4b8458059d9b3b5f46f5",
        "placeId": 55,
        "name": "loc55",
        "latitude": 40.4210603644662,
        "longitude": 129.041489369307,
        "hobby": 18,
        "favoriteFood": 10
      },
      ...
    ]
    ```

## Point System Feature
- `GET /quizzes/{quizId}`
  - **Description**: Retrieves quiz details by quiz ID.
  - **cURL Command**: `curl -X GET http://localhost:8080/quizzes/1`
  - **Expected Output**:
    ```json
    {
      "movieId": 1,
      "movieName": "Toy Story (1995)",
      "quizNum": 1,
      "quizBody": "movie1 quiz1 Body"
    }
    ```
- `POST /quizzes/{quizId}`
  - **Description**: Checks the user's answer for a quiz and returns the result.
  - **cURL Command**: `curl -X POST http://localhost:8080/quizzes/1 -d "{\"userId\": 1, \"answer\": \"A\"}" -H "Content-Type: application/json"`
  - **Expected Output**:
    ```json
    {
      "quizId":1,
      "userId":1,
      "resultMessage":"Wrong Answer!"
    }
    ```
- `POST /quizzes/reset/{quizId}`
  - **Description**: Resets the status of the quiz for a specific user.
  - **cURL Command**: `curl -X POST http://localhost:8080/quizzes/reset/1 -d "{\"userId\": 1}" -H "Content-Type: application/json"`
  - **Expected Output**:
    ```json
    {
      "quizId":1,
      "userId":1,
      "resultMessage":"Successfully reset"
    }
    ```
- `GET /users/{userId}`
  - **Description**: Retrieves user details by user ID.
  - **cURL Command**: `curl -X GET http://localhost:8080/users/1`
  - **Expected Output**:
    ```json
    {
      "userId": 1,
      "points": 74
    }
    ```

# Additional Notes

- The implemented features cover functionalities related to hosting virtual movie parties, accessing movie shooting location details, and managing user points. However, certain aspects mentioned in the initial proposal, such as real-time video streaming and live chat, are not fully implemented in the current codebase.
