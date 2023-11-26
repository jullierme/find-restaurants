package br.com.test.findRestaurants.parameters.validators;

import java.security.InvalidParameterException;

public interface PriceValidator {
    void validate(Integer value) throws InvalidParameterException;
}
