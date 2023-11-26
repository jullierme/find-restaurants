package br.com.test.findRestaurants.csv.cuisines;

import br.com.test.findRestaurants.exceptions.CsvFileException;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReadCuisinesImpl implements ReadCuisines {
    @Override
    public Map<Integer, String> getCuisines() throws CsvFileException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(Constants.CSV_CUISINES);
        Map<Integer, String> cuisineMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String cuisine = values[1];
                cuisineMap.put(id, cuisine);
            }
        } catch (IOException e) {
            throw new CsvFileException("Error reading file: " + Constants.CSV_CUISINES);
        }

        return cuisineMap;
    }
}
