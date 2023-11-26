package br.com.test.findRestaurants.csv.restaurants;

import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.exceptions.CsvFileException;

import java.util.List;
import java.util.Map;

public interface ReadRestaurants {
    List<RestaurantBean> getAllRestaurants(Map<Integer, String> cuisines) throws CsvFileException;
}
