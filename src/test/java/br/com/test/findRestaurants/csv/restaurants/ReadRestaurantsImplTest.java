package br.com.test.findRestaurants.csv.restaurants;

import br.com.test.findRestaurants.beans.RestaurantBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadRestaurantsImplTest {
    private ReadRestaurants readRestaurants;

    @BeforeEach
    void setUp() {
        readRestaurants = new ReadRestaurantsImpl();
    }

    @Test
    void shouldReadRestaurantsCsvFile() {
        // given
        int expectedRestaurantsSize = 200;


        // when
        List<RestaurantBean> restaurants = readRestaurants.getAllRestaurants(new HashMap<>());

        // then
        assertNotNull(restaurants);
        assertEquals(expectedRestaurantsSize, restaurants.size());
    }
}