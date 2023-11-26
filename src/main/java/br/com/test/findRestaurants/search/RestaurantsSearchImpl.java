package br.com.test.findRestaurants.search;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RestaurantsSearchImpl implements RestaurantsSearch {
    @Override
    public List<RestaurantBean> search(ParametersBean parameters, List<RestaurantBean> restaurants) {
        return restaurants.stream()
                .filter(restaurant -> isNameMatch(parameters.getRestaurantName(), restaurant.getName()))
                .filter(restaurant -> isCustomerRatingMatch(parameters.getCustomerRating(), restaurant.getCustomerRating()))
                .filter(restaurant -> isDistanceMatch(parameters.getDistance(), restaurant.getDistance()))
                .filter(restaurant -> isPriceMatch(parameters.getPrice(), restaurant.getPrice()))
                .filter(restaurant -> isCuisineMatch(parameters.getCuisine(), restaurant.getCuisine()))
                .sorted(Comparator.comparing(RestaurantBean::getDistance)
                        .thenComparing(Comparator.comparing(RestaurantBean::getCustomerRating).reversed())
                        .thenComparing(RestaurantBean::getPrice)
                        .thenComparing(restaurant -> new Random().nextInt())) // random sort in case of same results
                .limit(Constants.MAX_RESULT_LIST)
                .collect(Collectors.toList());
    }

    private boolean isNameMatch(String query, String name) {
        return isStringEmpty(query) || name.toLowerCase().contains(query.toLowerCase());
    }

    private boolean isCustomerRatingMatch(Integer query, Integer customerRating) {
        return query == null || query.compareTo(customerRating) <= 0;
    }

    private boolean isDistanceMatch(Integer query, Integer distance) {
        return query == null || query.compareTo(distance) >= 0;
    }

    private boolean isPriceMatch(Integer query, Integer price) {
        return query == null || query.compareTo(price) >= 0;
    }

    private boolean isCuisineMatch(String query, String cuisine) {
        return isStringEmpty(query) || cuisine.toLowerCase().contains(query.toLowerCase());
    }

    private boolean isStringEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
