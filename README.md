# weather-information-service
Simple weather information service  that provides current weather data for different cities.
This service offers 4 API endpoints for Create weather record, GET all cities weather records, GET particular city weather record & update  weather record for a city. 
OpenAPI specs can be found in the docs module.
This service uses H2 in-memory database.
Initially service will be providing weather data for 3 cities(Auckland, Hamilton & Wellington).
More information related to the service will be added, as changes will be made incrementally with addition of new features.


### Setting up ###

#### Download/Clone source code from the repo.

#### Compile to create jar file.
* Open terminal inside project root directory 'weather-information-service' and run:

```
./mvnw clean install
```

#### Run the jar file to get application running.

```
java -jar target/weather-information-service.jar
```

#### Follow API specs to test APIs




### Error-Codes
The service uses a 6 digit error code mechanism to further define the exact type of the error code within the same family of error codes.

**400001**	Property 'name' is missing or empty in the request body. 

**400002**	Property 'name', must be non-numeric string.

**400003**	Path parameter 'city' must be present.

**400004**	Path property 'city' must be non-numeric string.

**404001**	No weather records present for any cities. Create records first.

**404002**	No weather records present for requested city.


### Future considerations ###

This is simplest form of demonstrating Springboot rest microservice to manage single weather record for each city with basic validation. In future this can be made extensible by considering:
- Create seperate entities for City and Weather records.
- Managing different temperature units.
- Managing weather through Enums.
- Dockerised the application.
