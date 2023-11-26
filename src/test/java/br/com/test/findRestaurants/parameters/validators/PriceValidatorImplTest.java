package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceValidatorImplTest {

    private PriceValidator priceValidator;

    @BeforeEach
    void setUp() {
        priceValidator = new PriceValidatorImpl();
    }

    @Test
    void shouldValidateWithoutException() {
        // given
        int validPrice = 10;

        // when
        // then
        assertDoesNotThrow(() -> priceValidator.validate(validPrice));
    }

    @Test
    void shouldNotAcceptPriceLessThanMinimum() {
        // Given
        int invalidLowPrice = Constants.MINIMUM_PRICE - 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> priceValidator.validate(invalidLowPrice));
        assertEquals(ex.getMessage(), Constants.INVALID_PRICE_MESSAGE);
    }

    @Test
    void shouldNotAcceptPriceBiggerThanMaximum() {
        // Given
        int invalidHighPrice = Constants.MAXIMUM_PRICE + 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> priceValidator.validate(invalidHighPrice));
        assertEquals(ex.getMessage(), Constants.INVALID_PRICE_MESSAGE);
    }

    @Test
    void shouldIgnoreNullRating() {
        // Given
        Integer nullPrice = null;

        // when
        // then
        assertDoesNotThrow(() -> priceValidator.validate(nullPrice));
    }
}