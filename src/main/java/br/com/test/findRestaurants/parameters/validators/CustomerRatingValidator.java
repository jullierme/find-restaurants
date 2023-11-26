package br.com.test.findRestaurants.parameters.validators;

import java.security.InvalidParameterException;

public interface CustomerRatingValidator {
    void validate(Integer value) throws InvalidParameterException;
}
