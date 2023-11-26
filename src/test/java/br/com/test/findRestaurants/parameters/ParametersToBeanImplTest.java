package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParametersToBeanImplTest {

    private ParametersToBean parametersToBean;

    @BeforeEach
    void setUp() {
        parametersToBean = new ParametersToBeanImpl();
    }

    @Test
    void shouldThrowsExceptionWhenArgsAreEmpty() {
        // given
        String[] args = new String[0];

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> parametersToBean.toBean(args));
        assertEquals(ex.getMessage(), Constants.NO_ARGS_MESSAGE);
    }

    @Test
    void shouldThrowsExceptionWhenFirstArgumentIsEmpty() {
        // given
        String[] args = new String[1];
        args[0] = "";

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> parametersToBean.toBean(args));
        assertEquals(ex.getMessage(), Constants.NO_ARGS_MESSAGE);
    }

    @Test
    void shouldThrowsExceptionWhenFirstArgumentIsInvalidJson() {
        // given
        String[] args = new String[1];
        args[0] = "{invalid json}";

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> parametersToBean.toBean(args));
        assertEquals(ex.getMessage(), Constants.INVALID_ARGS_MESSAGE);
    }

    @Test
    void shouldConvertJsonToParametersBean() {
        // given
        String cuisine = "chinese";
        String restaurantName = "chinese restaurant";
        int customerRating = 3;
        int distance = 1;
        int price = 10;

        String jsonString = String.format("{\"cuisine\":\"%s\",\"name\":\"%s\",\"customerRating\":%d,\"distance\":%d,\"price\":%d}",
                cuisine, restaurantName, customerRating, distance, price);
        String[] args = new String[1];
        args[0] = jsonString;

        // when
        ParametersBean bean = parametersToBean.toBean(args);

        // then
        assertEquals(bean.getCuisine(), cuisine);
        assertEquals(bean.getCustomerRating(), customerRating);
        assertEquals(bean.getDistance(), distance);
        assertEquals(bean.getPrice(), price);

    }

    /*@Test
    void shouldThrowsExceptionWhenFirstArgumentIsValidJsonButInvalidProperty() {
        // given
        String cuisine = "chinese";
        String josnString = String.format("{\"cuisine\":\"%s\",\"invalidProperty\":\"invalidValue\"}", cuisine);
        String[] args = new String[1];
        args[0] = josnString;

        // when
        // then
        ParametersException ex = assertThrows(ParametersException.class, () -> parametersToBean.toBean(args));
        assertEquals(ex.getMessage(), Constants.INVALID_ARGS_MESSAGE);
    }*/
}