package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class PriceValidatorImpl implements PriceValidator {
    @Override
    public void validate(Integer value) throws ParametersException {
        if (value != null && (value < Constants.MINIMUM_PRICE || value > Constants.MAXIMUM_PRICE))
            throw new ParametersException(Constants.INVALID_PRICE_MESSAGE);
    }
}
