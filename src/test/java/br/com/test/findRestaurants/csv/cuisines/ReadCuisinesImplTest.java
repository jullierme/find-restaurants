package br.com.test.findRestaurants.csv.cuisines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadCuisinesImplTest {

    private ReadCuisines readCuisines;

    @BeforeEach
    void setUp() {
        readCuisines = new ReadCuisinesImpl();
    }

    @Test
    void shouldReadCuisinesCsvFile() {
        // given
        int expectedCuisinesSize = 19;

        // when
        Map<Integer, String> cuisines = readCuisines.getCuisines();

        // then
        assertNotNull(cuisines);
        assertEquals(expectedCuisinesSize, cuisines.size());
    }
}