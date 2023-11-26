package br.com.test.findRestaurants.search;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RestaurantsSearchImplTest {
    private RestaurantsSearch restaurantsSearch;
    private List<RestaurantBean> restaurantList;

    @BeforeEach
    void setUp() {
        restaurantsSearch = new RestaurantsSearchImpl();
        restaurantList = Arrays.asList(
                new RestaurantBean("Restaurant 1", 4, 10, 30, "Italian"),
                new RestaurantBean("Restaurant 2", 3, 10, 20, "Japanese"),
                new RestaurantBean("Restaurant 3", 1, 9, 50, "American"),
                new RestaurantBean("Restaurant 4", 1, 8, 11, "Chinese"),
                new RestaurantBean("Restaurant 5", 2, 8, 13, "Thai"),
                new RestaurantBean("Restaurant 6", 3, 7, 44, "Japanese"),
                new RestaurantBean("Restaurant 7", 4, 5, 20, "Japanese"),
                new RestaurantBean("Restaurant 8", 5, 5, 10, "Turkish"),
                new RestaurantBean("Restaurant 9", 1, 5, 27, "Korean"),
                new RestaurantBean("Restaurant 10", 5, 1, 32, "Vietnamese"),
                new RestaurantBean("Restaurant 11", 5, 1, 43, "Indian"),
                new RestaurantBean("Restaurant 12", 2, 2, 50, "Spanish"),
                new RestaurantBean("Restaurant 13", 5, 3, 50, "Greek"),
                new RestaurantBean("Restaurant 14", 4, 4, 60, "Malaysian"),
                new RestaurantBean("Restaurant 15", 4, 5, 70, "Indonesian")
        );
    }

    /**
     * A Restaurant Name match is defined as an exact or partial String match.
     */
    @Test
    void shouldFilterByName() {
        // given
        int expectedSize = Constants.MAX_RESULT_LIST;
        String restaurantName = "Restaurant 1";
        ParametersBean parameters = new ParametersBean();
        parameters.setRestaurantName(restaurantName);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        assertTrue(filteredList.get(0).getName().contains(restaurantName));
    }

    /**
     * A Customer Rating match is defined as a rating greater than or equal to the supplied customer rating.
     */
    @Test
    void shouldFilterByRating() {
        // given
        int expectedSize = Constants.MAX_RESULT_LIST;
        int customerRating = 4;
        ParametersBean parameters = new ParametersBean();
        parameters.setCustomerRating(customerRating);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        for (RestaurantBean restaurant : filteredList) {
            assertTrue(restaurant.getCustomerRating() >= customerRating);
        }
    }

    /**
     * A Distance match is defined as a distance less than or equal to the supplied distance.
     */
    @Test
    void shouldFilterByDistance() {
        // given
        int distance = 10;
        int expectedSize = Constants.MAX_RESULT_LIST;
        ParametersBean parameters = new ParametersBean();
        parameters.setDistance(distance);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        for (RestaurantBean restaurant : filteredList) {
            assertTrue(restaurant.getDistance() <= distance);
        }
    }

    /**
     * A Price match is defined as a price less than or equal to the supplied price.
     */
    @Test
    void shouldFilterByPrice() {
        // given
        int price = 30;
        int expectedSize = Constants.MAX_RESULT_LIST;
        ParametersBean parameters = new ParametersBean();
        parameters.setPrice(price);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        for (RestaurantBean restaurant : filteredList) {
            assertTrue(restaurant.getPrice() <= price);
        }
    }

    /**
     * A Cuisine match is defined as an exact or partial String match.
     */
    @Test
    void shouldFilterByCuisine() {
        // given
        String cuisine = "Japanese";
        int expectedSize = 3;
        ParametersBean parameters = new ParametersBean();
        parameters.setCuisine(cuisine);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        assertEquals(cuisine, filteredList.get(0).getCuisine());
    }

    /**
     * A Restaurant Name match is defined as an exact or partial String match.
     * A Customer Rating match is defined as a rating greater than or equal to the supplied customer rating.
     * A Distance match is defined as a distance less than or equal to the supplied distance.
     * A Price match is defined as a price less than or equal to the supplied price.
     * A Cuisine match is defined as an exact or partial String match.
     * The five parameters are holding an “AND” relationship.
     */
    @Test
    void shouldFilterByAll5Parameters() {
        // given
        String restaurantName = "Restau";
        String cuisine = "Itali";
        int customerRating = 4;
        int distance = 10;
        int price = 30;
        int expectedSize = 1;
        ParametersBean parameters = new ParametersBean();
        parameters.setRestaurantName(restaurantName);
        parameters.setCuisine(cuisine);
        parameters.setCustomerRating(customerRating);
        parameters.setDistance(distance);
        parameters.setPrice(price);

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, filteredList.size());
        assertTrue(filteredList.get(0).getCuisine().contains(cuisine));
        assertTrue(filteredList.get(0).getName().contains(restaurantName));
        assertEquals(customerRating, filteredList.get(0).getCustomerRating());
        assertEquals(distance, filteredList.get(0).getDistance());
        assertEquals(price, filteredList.get(0).getPrice());
    }


    @Test
    void shouldSortByDistanceFirst() {
        // given
        int customerRating = 3;
        int distance = 1;
        int price = 32;
        int expectedSize = 5;
        ParametersBean parameters = new ParametersBean();
        parameters.setCustomerRating(customerRating);
        parameters.setDistance(distance);
        parameters.setPrice(price);

        restaurantList = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new RestaurantBean("Restaurant " + i,
                        new Random().nextInt(customerRating) + 3,
                        new Random().nextInt(distance) + 1,
                        new Random().nextInt(price) + 1,
                        "Italian"))
                .collect(Collectors.toList());

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        RestaurantBean restaurant1 = filteredList.get(0);
        RestaurantBean restaurant2 = filteredList.get(1);
        RestaurantBean restaurant3 = filteredList.get(2);
        RestaurantBean restaurant4 = filteredList.get(3);
        RestaurantBean restaurant5 = filteredList.get(4);

        assertEquals(expectedSize, filteredList.size());
        assertDistance(restaurant1, restaurant2);
        assertDistance(restaurant2, restaurant3);
        assertDistance(restaurant3, restaurant4);
        assertDistance(restaurant4, restaurant5);
    }

    @Test
    void shouldSortByCustomerRating() {
        // given
        int customerRating = 3;
        int distance = 10;
        int price = 32;
        int expectedSize = Constants.MAX_RESULT_LIST;
        ParametersBean parameters = new ParametersBean();
        parameters.setCustomerRating(customerRating);
        parameters.setDistance(distance);
        parameters.setPrice(price);

        restaurantList = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new RestaurantBean("Restaurant " + i,
                        new Random().nextInt(customerRating) + 3,
                        distance,
                        price,
                        "Italian"))
                .collect(Collectors.toList());


        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        RestaurantBean restaurant1 = filteredList.get(0);
        RestaurantBean restaurant2 = filteredList.get(1);
        RestaurantBean restaurant3 = filteredList.get(2);
        RestaurantBean restaurant4 = filteredList.get(3);
        RestaurantBean restaurant5 = filteredList.get(4);

        assertEquals(expectedSize, filteredList.size());
        assertCustomerRating(restaurant1, restaurant2);
        assertCustomerRating(restaurant2, restaurant3);
        assertCustomerRating(restaurant3, restaurant4);
        assertCustomerRating(restaurant4, restaurant5);
    }

    @Test
    void shouldSortByPrice() {
        // given
        int customerRating = 3;
        int distance = 10;
        int price = 50;
        int expectedSize = 5;
        ParametersBean parameters = new ParametersBean();
        parameters.setCustomerRating(customerRating);
        parameters.setDistance(distance);
        parameters.setPrice(price);

        restaurantList = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new RestaurantBean("Restaurant " + i, customerRating, distance, new Random().nextInt(41) + 10, "Italian"))
                .collect(Collectors.toList());

        // when
        List<RestaurantBean> filteredList = restaurantsSearch.search(parameters, restaurantList);

        // then
        RestaurantBean restaurant1 = filteredList.get(0);
        RestaurantBean restaurant2 = filteredList.get(1);
        RestaurantBean restaurant3 = filteredList.get(2);
        RestaurantBean restaurant4 = filteredList.get(3);
        RestaurantBean restaurant5 = filteredList.get(4);

        assertEquals(expectedSize, filteredList.size());
        assertPrice(restaurant1, restaurant2);
        assertPrice(restaurant2, restaurant3);
        assertPrice(restaurant3, restaurant4);
        assertPrice(restaurant4, restaurant5);
    }

    /**
     * NOTE: When sorting the lists randomly, it may be that, by coincidence,
     * the lists are in the same order. So, in a dumb way, the test will be run a maximum
     * of 5 times, to reduce the chance of two randomly generated lists being the
     * same. In this case, it can still happen, but with an absolutely low probability.
     */
    @Test
    void shouldSortRandomlyWhenTheSortStillTheSame() {
        // given
        boolean listsAreDifferent = false;
        int attempts = 0;
        int maxAttempts = 5;
        ParametersBean parameters = new ParametersBean();
        parameters.setRestaurantName("Restaurant");
        restaurantList = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new RestaurantBean("Restaurant " + i, 5, 1, 10, "Other"))
                .collect(Collectors.toList());

        // when
        while (!listsAreDifferent && attempts < maxAttempts) {
            List<RestaurantBean> firstSortedList = restaurantsSearch.search(parameters, restaurantList);
            List<RestaurantBean> secondSortedList = restaurantsSearch.search(parameters, restaurantList);

            if (!firstSortedList.equals(secondSortedList)) {
                listsAreDifferent = true;
            }
            attempts++;
        }

        // then
        assertTrue(listsAreDifferent);
    }

    @Test
    void shouldLimitTheResultList() {
        // given
        int expectedSize = Constants.MAX_RESULT_LIST;
        ParametersBean parameters = new ParametersBean();
        parameters.setRestaurantName("Restaurant");
        restaurantList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new RestaurantBean("Restaurant " + i, 5, 1, 10, "Other"))
                .collect(Collectors.toList());


        // when
        List<RestaurantBean> resultList = restaurantsSearch.search(parameters, restaurantList);

        // then
        assertEquals(expectedSize, resultList.size());
    }

    private void assertDistance(RestaurantBean restaurant1, RestaurantBean restaurant2) {
        assertTrue(restaurant1.getDistance() <= restaurant2.getDistance());
    }

    private void assertCustomerRating(RestaurantBean restaurant1, RestaurantBean restaurant2) {
        assertTrue(restaurant1.getCustomerRating() >= restaurant2.getCustomerRating());
    }

    private void assertPrice(RestaurantBean restaurant1, RestaurantBean restaurant2) {
        assertTrue(restaurant1.getPrice() <= restaurant2.getPrice());
    }
}