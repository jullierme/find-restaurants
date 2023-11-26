package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.parameters.validators.CustomerRatingValidator;
import br.com.test.findRestaurants.parameters.validators.DistanceValidator;
import br.com.test.findRestaurants.parameters.validators.PriceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateParametersImpl implements ValidateParameters {
    private final PriceValidator priceValidator;
    private final CustomerRatingValidator customerRatingValidator;
    private final DistanceValidator distanceValidator;

    @Override
    public void validate(ParametersBean parametersBean) throws ParametersException {
        priceValidator.validate(parametersBean.getPrice());
        customerRatingValidator.validate(parametersBean.getCustomerRating());
        distanceValidator.validate(parametersBean.getDistance());
    }
}
