package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRatingValidatorImplTest {

    private CustomerRatingValidator customerRatingValidator;

    @BeforeEach
    void setUp() {
        customerRatingValidator = new CustomerRatingValidatorImpl();
    }

    @Test
    void shouldValidateWithoutException() {
        // given
        int validRating = 3;

        // when
        // then
        assertDoesNotThrow(() -> customerRatingValidator.validate(validRating));
    }

    @Test
    void shouldNotAcceptRatingLessThanMinimum() {
        // given
        int invalidLowRating = Constants.MINIMUM_RATING - 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> customerRatingValidator.validate(invalidLowRating));
        assertEquals(ex.getMessage(), Constants.INVALID_RATING_MESSAGE);
    }

    @Test
    void shouldNotAcceptRatingBiggerThanMaximum() {
        // given
        int invalidHighRating = Constants.MAXIMUM_RATING + 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> customerRatingValidator.validate(invalidHighRating));
        assertEquals(ex.getMessage(), Constants.INVALID_RATING_MESSAGE);
    }

    @Test
    void shouldIgnoreNullRating() {
        // given
        Integer nullRating = null;

        // when
        // then
        assertDoesNotThrow(() -> customerRatingValidator.validate(nullRating));
    }
}
