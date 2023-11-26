package br.com.test.findRestaurants.csv.restaurants;

import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.exceptions.CsvFileException;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReadRestaurantsImpl implements ReadRestaurants {
    @Override
    public List<RestaurantBean> getAllRestaurants(Map<Integer, String> cuisines) throws CsvFileException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.CSV_RESTAURANTS);
        List<RestaurantBean> restaurants = new ArrayList<>();

        if (inputStream == null)
            throw new CsvFileException("CSV file not found: " + Constants.CSV_RESTAURANTS);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                String name = values[0];
                int rating = Integer.parseInt(values[1]);
                int distance = Integer.parseInt(values[2]);
                int price = Integer.parseInt(values[3]);
                int cuisineId = Integer.parseInt(values[4]);
                String cuisine = cuisines.getOrDefault(cuisineId, "Other");
                RestaurantBean restaurant = new RestaurantBean(name, rating, distance, price, cuisine);
                restaurants.add(restaurant);
            }
        } catch (IOException e) {
            throw new CsvFileException("Error reading file: " + Constants.CSV_RESTAURANTS);
        }

        return restaurants;
    }
}
