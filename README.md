# Project Overview

- Created the project using the Spring Boot website to avoid wasting time configuring it. Used Spring, JUnit 5 and
  Lombok for
  coding and testing. No additional framework was deemed necessary for the project's scope.

## Execution & Decision Making

- I utilized Java for this assessment.

- To optimize efficiency due to the limited data volume in the CSV, I chose to load it into memory. With all the data in
  memory, I executed the restaurant search based on the provided parameters.

- Rather than returning an empty list, the system notifies if no records are found.

- Given the nature of this application as a one-time execution, I abstained from storing the CSV's read values.

- Developed an executable .jar that showcases search results in the console, effectively meeting the project's
  requirements.

- I opted to create straightforward unit tests for all classes, aiming to cover a significant number of scenarios.

- I refrained from implementing integration tests as I deemed them unnecessary for this project.

- Comments weren't included in the code as I believe the code itself should be self-explanatory and understandable.

## Front-End

- Due to time constraints, the optional front-end wasn't implemented. However, I'm open to doing it if needed.

## Execution Commands

- To generate the JAR, I used the command:

```shell
./gradlew build
```

- To execute the JAR, use:

```shell
java -jar best-matched-restaurants-jsb-0.0.1-SNAPSHOT.jar '{"customerRating":3,"price":15,"restaurantName":"Yummy","cuisine":"an","customerRating":3, "distance":1}'
```

## Project Structure & Components

- Initialized the Spring Boot application through the `Application` class.

- Program execution is managed by the `ApplicationRunner` class after project initialization.

- Conversion of JSON to a bean is handled by the `ParametersToBean` class.

- Validation of parameters against test requirements is managed by classes like `PriceValidator`, `DistanceValidator`,
  and `CustomerRatingValidator`.

- All validators are triggered at a central point in the `ValidateParameters` class.

- Reading of the cuisine CSV is managed by the `ReadCuisines` class.

- Reading of the restaurant CSV, where the restaurant object is populated with cuisine descriptions, is handled by
  the `ReadRestaurants` class.

- A single class, `LoadCsvFiles`, is responsible for invoking the reading of all CSVs.

- The search is performed through the `RestaurantsSearch` class with user parameters converted to a `ParametersBean`
  object and a list of `RestaurantBean` objects.

- Exceptions that might occur during program execution are handled by the `CsvFileException` and `ParametersException`
  classes.

- A constants class, `Constants`, maintains static values in a centralized file.

## Project Repository

- The source code repository is available
  at: [https://github.com/jullierme/find-restaurants](https://github.com/jullierme/find-restaurants)

- The executable JAR is available
  at: [https://github.com/jullierme/find-restaurants/tree/main/executable](https://github.com/jullierme/find-restaurants/tree/main/executable)

## Notes

- Big thanks for the opportunity :)