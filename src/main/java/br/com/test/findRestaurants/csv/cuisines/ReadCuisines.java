package br.com.test.findRestaurants.csv.cuisines;

import br.com.test.findRestaurants.exceptions.CsvFileException;

import java.util.Map;

public interface ReadCuisines {
    Map<Integer, String> getCuisines() throws CsvFileException;
}
