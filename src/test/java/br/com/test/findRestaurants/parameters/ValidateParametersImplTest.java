package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.parameters.validators.CustomerRatingValidator;
import br.com.test.findRestaurants.parameters.validators.DistanceValidator;
import br.com.test.findRestaurants.parameters.validators.PriceValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateParametersImplTest {

    @Mock
    private PriceValidator priceValidator;
    @Mock
    private CustomerRatingValidator customerRatingValidator;
    @Mock
    private DistanceValidator distanceValidator;
    @InjectMocks
    private ValidateParametersImpl validateParametersImpl;

    @Test
    void shouldValidateParameters() throws ParametersException {
        // given
        ParametersBean parametersBean = new ParametersBean();
        parametersBean.setDistance(5);
        parametersBean.setPrice(11);
        parametersBean.setCustomerRating(3);

        // when
        validateParametersImpl.validate(parametersBean);

        // then
        verify(priceValidator, times(1)).validate(parametersBean.getPrice());
        verify(customerRatingValidator, times(1)).validate(parametersBean.getCustomerRating());
        verify(distanceValidator, times(1)).validate(parametersBean.getDistance());
    }
}