package br.com.test.findRestaurants.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class RestaurantBean {
    private final String name;
    private final int customerRating;
    private final int distance;
    private final int price;
    private final String cuisine;
}
