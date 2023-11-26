package br.com.test.findRestaurants.csv.load;

import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.csv.cuisines.ReadCuisines;
import br.com.test.findRestaurants.csv.restaurants.ReadRestaurants;
import br.com.test.findRestaurants.exceptions.CsvFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadCsvFilesImpl implements LoadCsvFiles {
    private final ReadCuisines readCuisines;
    private final ReadRestaurants readRestaurants;

    @Override
    public List<RestaurantBean> getAllRestaurants() throws CsvFileException {
        return readRestaurants.getAllRestaurants(readCuisines.getCuisines());
    }
}
