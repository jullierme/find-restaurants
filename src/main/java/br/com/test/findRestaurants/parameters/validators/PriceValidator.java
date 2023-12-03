package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;

public interface PriceValidator {
    void validate(Integer value) throws ParametersException;
}
