package br.com.test.findRestaurants.parameters.validators;

import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceValidatorImplTest {

    private DistanceValidator distanceValidator;

    @BeforeEach
    void setUp() {
        distanceValidator = new DistanceValidatorImpl();
    }

    @Test
    void shouldValidateWithoutException() {
        // given
        int validDistance = 1;

        // when
        // then
        assertDoesNotThrow(() -> distanceValidator.validate(validDistance));
    }

    @Test
    void shouldNotAcceptDistanceLessThanMinimum() {
        // Given
        int invalidLowDistance = Constants.MINIMUM_DISTANCE - 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> distanceValidator.validate(invalidLowDistance));
        assertEquals(ex.getMessage(), Constants.INVALID_DISTANCE_MESSAGE);
    }

    @Test
    void shouldNotAcceptDistanceBiggerThanMaximum() {
        // Given
        int invalidHighDistance = Constants.MAXIMUM_DISTANCE + 1;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> distanceValidator.validate(invalidHighDistance));
        assertEquals(ex.getMessage(), Constants.INVALID_DISTANCE_MESSAGE);
    }

    @Test
    void shouldIgnoreNullRating() {
        // Given
        Integer nullDistance = null;

        // when
        // then
        assertDoesNotThrow(() -> distanceValidator.validate(nullDistance));
    }
}