package br.com.test.findRestaurants.exceptions;

public class ParametersException extends RuntimeException {
    public ParametersException(String errorMessage) {
        super(errorMessage);
    }
}
