package br.com.test.findRestaurants.search;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.beans.RestaurantBean;

import java.util.List;

public interface RestaurantsSearch {
    List<RestaurantBean> search(ParametersBean parameters, List<RestaurantBean> restaurants);
}
