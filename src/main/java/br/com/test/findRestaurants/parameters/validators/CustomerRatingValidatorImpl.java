package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class CustomerRatingValidatorImpl implements CustomerRatingValidator {
    @Override
    public void validate(Integer value) throws ParametersException {
        if (value != null && (value < Constants.MINIMUM_RATING || value > Constants.MAXIMUM_RATING))
            throw new ParametersException(Constants.INVALID_RATING_MESSAGE);
    }
}
