package br.com.test.findRestaurants.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParametersBean {
    private String restaurantName;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisine;
}
