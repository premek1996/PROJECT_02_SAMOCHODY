package com.app.service;

import com.app.persistence.model.car.Car;
import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.service.utils.CarsUtils.BMW;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceFindCarsWithComponentsTest {

    private final CarService carService;

    @Test
    @DisplayName("When List with components is null")
    void test1() {
        assertThatThrownBy(() -> carService.findCarsWithComponents(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("List with components is null");
    }

    @Test
    @DisplayName("When there are no cars with given components")
    void test2() {
        assertThat(carService.findCarsWithComponents(List.of("AIR CONDITIONING")))
                .isEmpty();
    }

    @Test
    @DisplayName("When there are cars with given components")
    void test3() {
        List<Car> expectedCars = List.of(BMW);
        assertThat(carService.findCarsWithComponents(List.of("ABS")))
                .containsExactlyElementsOf(expectedCars);
    }

}
