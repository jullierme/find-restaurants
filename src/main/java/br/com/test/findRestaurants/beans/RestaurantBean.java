package br.com.test.findRestaurants.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RestaurantBean {
    private String name;
    private int customerRating;
    private int distance;
    private int price;
    private String cuisine;
}
