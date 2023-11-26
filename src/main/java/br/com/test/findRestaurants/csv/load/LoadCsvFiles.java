package br.com.test.findRestaurants.csv.load;

import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.exceptions.CsvFileException;

import java.util.List;

public interface LoadCsvFiles {
    List<RestaurantBean> getAllRestaurants() throws CsvFileException;
}
