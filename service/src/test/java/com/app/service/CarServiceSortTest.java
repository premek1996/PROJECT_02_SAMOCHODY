package com.app.service;

import com.app.persistence.model.car.Car;
import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import com.app.service.type.Sort;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.service.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceSortTest {

    private final CarService carService;

    @Test
    @DisplayName("When Sort object is null")
    void test1() {
        assertThatThrownBy(() -> carService.sort(null, true))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Sort object is null");
    }

    @Test
    @DisplayName("When cars collection is sorted by engine power ascending")
    void test2() {
        List<Car> expectedSortedCars = List.of(MAZDA, AUDI, BMW);
        List<Car> sortedCars = carService.sort(Sort.ENGINE_POWER, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by engine power descending")
    void test3() {
        List<Car> expectedSortedCars = List.of(BMW, AUDI, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.ENGINE_POWER, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by components number ascending")
    void test4() {
        List<Car> expectedSortedCars = List.of(AUDI, BMW, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.COMPONENTS_NUMBER, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by components number descending")
    void test5() {
        List<Car> expectedSortedCars = List.of(AUDI, BMW, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.COMPONENTS_NUMBER, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by wheel size ascending")
    void test6() {
        List<Car> expectedSortedCars = List.of(AUDI, MAZDA, BMW);
        List<Car> sortedCars = carService.sort(Sort.WHEEL_SIZE, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by wheel size descending")
    void test7() {
        List<Car> expectedSortedCars = List.of(BMW, MAZDA, AUDI);
        List<Car> sortedCars = carService.sort(Sort.WHEEL_SIZE, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

}
