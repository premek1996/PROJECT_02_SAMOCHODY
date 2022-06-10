package com.app.service;

import com.app.persistence.model.car.Car;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.service.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetCarsWithMileages {

    private final CarService carService;

    @Test
    @DisplayName("When cars are available")
    void test1() {
        Map<Car, Integer> expectedCarsWithMileages = Map.of(AUDI, 12000, BMW, 13000, MAZDA, 15000);
        assertThat(carService.getCarsWithMileages())
                .containsExactlyInAnyOrderEntriesOf(expectedCarsWithMileages);
    }

}
