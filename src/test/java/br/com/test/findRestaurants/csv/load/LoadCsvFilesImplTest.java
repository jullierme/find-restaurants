package br.com.test.findRestaurants.csv.load;

import br.com.test.findRestaurants.beans.RestaurantBean;
import br.com.test.findRestaurants.csv.cuisines.ReadCuisines;
import br.com.test.findRestaurants.csv.restaurants.ReadRestaurants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoadCsvFilesImplTest {
    @Mock
    private ReadCuisines readCuisines;

    @Mock
    private ReadRestaurants readRestaurants;

    @InjectMocks
    private LoadCsvFilesImpl loadCsvFiles;

    @Test
    void shouldGetAllRestaurants() {
        // given
        Map<Integer, String> cuisinesMap = new HashMap<>();
        List<RestaurantBean> expectedRestaurantList = new ArrayList<>();
        when(readCuisines.getCuisines()).thenReturn(cuisinesMap);
        when(readRestaurants.getAllRestaurants(cuisinesMap)).thenReturn(expectedRestaurantList);

        // when
        List<RestaurantBean> result = loadCsvFiles.getAllRestaurants();

        // then
        assertEquals(expectedRestaurantList, result);
        verify(readCuisines, times(1)).getCuisines();
        verify(readRestaurants, times(1)).getAllRestaurants(cuisinesMap);
    }
}