package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
public class DistanceValidatorImpl implements DistanceValidator {
    @Override
    public void validate(Integer value) throws InvalidParameterException {
        if (value != null && (value < Constants.MINIMUM_DISTANCE || value > Constants.MAXIMUM_DISTANCE))
            throw new ParametersException(Constants.INVALID_DISTANCE_MESSAGE);
    }
}
