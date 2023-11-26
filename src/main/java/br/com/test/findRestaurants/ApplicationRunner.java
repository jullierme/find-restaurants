package br.com.test.findRestaurants;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.csv.load.LoadCsvFiles;
import br.com.test.findRestaurants.exceptions.CsvFileException;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.parameters.ParametersToBean;
import br.com.test.findRestaurants.parameters.ValidateParameters;
import br.com.test.findRestaurants.search.RestaurantsSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {
    private final LoadCsvFiles loadCsvFiles;
    private final ParametersToBean parametersToBean;
    private final ValidateParameters validateParameters;
    private final RestaurantsSearch restaurantsSearch;

    @Override
    public void run(String[] args) {
        final List<RestaurantBean> restaurants = runSearch(args);

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
        } else {
            for (RestaurantBean restaurant : restaurants) {
                System.out.println(restaurant.toString());
            }
        }
    }

    /**
     * --->
     * Develop a basic search function that allows your colleagues to search
     * those restaurants to help them find where they would like to have lunch.
     * <---
     */
    private List<RestaurantBean> runSearch(String[] args) {
        try {
            final ParametersBean parametersBean = parametersToBean.toBean(args);
            validateParameters.validate(parametersBean);
            final List<RestaurantBean> allRestaurants = loadCsvFiles.getAllRestaurants();
            return restaurantsSearch.search(parametersBean, allRestaurants);
        } catch (ParametersException | CsvFileException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
