package br.com.test.findRestaurants.util;

public class Constants {
    public static final String CSV_CUISINES = "csv/cuisines.csv";
    public static final String CSV_RESTAURANTS = "csv/restaurants.csv";
    public static final int MINIMUM_PRICE = 10;
    public static final int MAXIMUM_PRICE = 50;
    public static final int MINIMUM_RATING = 1;
    public static final int MAXIMUM_RATING = 5;
    public static final int MINIMUM_DISTANCE = 1;
    public static final int MAXIMUM_DISTANCE = 10;
    public static final int MAX_RESULT_LIST = 5;
    public static final String INVALID_DISTANCE_MESSAGE = String.format("The distance must be between %s and %s", MINIMUM_DISTANCE, MAXIMUM_DISTANCE);
    public static final String INVALID_RATING_MESSAGE = String.format("The rating must be between %s and %s", MINIMUM_RATING, MAXIMUM_RATING);
    public static final String INVALID_PRICE_MESSAGE = String.format("The price must be between %s and %s", MINIMUM_PRICE, MAXIMUM_PRICE);

    public static final String NO_ARGS_MESSAGE = "No parameters were informed";
    public static final String INVALID_ARGS_MESSAGE = "The parameter passed is not valid.";
}
