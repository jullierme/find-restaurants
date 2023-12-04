package br.com.test.findRestaurants;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.csv.load.LoadCsvFiles;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.parameters.ParametersToBean;
import br.com.test.findRestaurants.parameters.ValidateParameters;
import br.com.test.findRestaurants.search.RestaurantsSearch;
import br.com.test.findRestaurants.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationRunnerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Mock
    private LoadCsvFiles loadCsvFilesMock;
    @Mock
    private ParametersToBean parametersToBeanMock;
    @Mock
    private ValidateParameters validateParametersMock;
    @Mock
    private RestaurantsSearch restaurantsSearchMock;
    @InjectMocks
    private ApplicationRunner applicationRunner;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void shouldNotFoundRestaurants() throws ParametersException {
        // given
        when(parametersToBeanMock.toBean(any())).thenReturn(new ParametersBean());
        when(loadCsvFilesMock.getAllRestaurants()).thenReturn(new ArrayList<>());
        when(restaurantsSearchMock.search(any(), any())).thenReturn(new ArrayList<>());
        String jsonString = "{\"name\":\"AAA\"}";

        // when
        applicationRunner.run(new String[]{jsonString});

        // then
        assertEquals(Constants.NO_RESTAURANTS_FOUND, outputStreamCaptor.toString().trim());
    }

    @Test
    void shouldFoundRestaurants() throws ParametersException {
        // given
        List<RestaurantBean> restaurantList = new ArrayList<>();
        restaurantList.add(new RestaurantBean("Restaurant 1", 4, 10, 30, "Italian"));
        when(parametersToBeanMock.toBean(any())).thenReturn(new ParametersBean());
        when(loadCsvFilesMock.getAllRestaurants()).thenReturn(restaurantList);
        when(restaurantsSearchMock.search(any(), any())).thenReturn(restaurantList);
        String jsonString = "{\"name\":\"Restaurant 2\"}";
        String expectedResult = "RestaurantBean(name=Restaurant 1, customerRating=4, distance=10, price=30, cuisine=Italian)";

        // when
        applicationRunner.run(new String[]{jsonString});

        // then
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }
}