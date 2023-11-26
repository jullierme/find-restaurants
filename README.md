# Project Overview

- Created the project using the Spring Boot website to avoid wasting time configuring it. Used Spring, JUnit 5 and
  Lombok for
  coding and testing. No additional framework was deemed necessary for the project's scope.

## Execution & Decision Making

- I used Java for this assessment

- I decided to load the CSV data into memory since there are only a few data points. From this list with all the data in
  memory, I performed the restaurant search based on the provided parameters.

- In case of no results, instead of returning an empty list, the system informs if no records are found.

- Since it's a one-time execution application, I didn't store the CSV's read values.

- Created an executable .jar that returns search results in the console. For this specific application, this approach
  fulfilled the requirements.
- I decided to create simple unit tests for all classes. There are some cases that might not be covered but,
  the idea was to have a good amount of tests.

- Integrations tests was also not implemented because I think it's not necessary for this project.

- I didn't add comments into the code because, I believe that the code must be good enough to be
  understandable.

## Front-End

- Due to time constraints, the optional front-end wasn't implemented. However, I'm open to doing it if needed.

## Execution Commands

- To generate the JAR, I used the command:

```shell
./gradlew build
```

- To execute the JAR, use:

```shell
java -jar findRestaurants-0.0.1-SNAPSHOT.jar '{"customerRating":3,"price":15,"restaurantName":"Yummy","cuisine":"an","customerRating":3, "distance":1}'
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

## Notes

- Big thanks for the opportunity :)