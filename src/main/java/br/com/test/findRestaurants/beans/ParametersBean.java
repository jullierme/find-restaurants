package br.com.test.findRestaurants.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametersBean {
    private String restaurantName;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisine;
}
