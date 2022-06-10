package com.app.service;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.engine.EngineType;
import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.service.utils.CarsUtils.AUDI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceFindCarsWithEngineTypeTest {

    private final CarService carService;

    @Test
    @DisplayName("When EngineType object is null")
    void test1() {
        assertThatThrownBy(() -> carService.findCarsWithEngineType(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("EngineType object is null");
    }

    @Test
    @DisplayName("When there are no cars with given EngineType")
    void test2() {
        assertThat(carService.findCarsWithEngineType(EngineType.LPG))
                .isEmpty();
    }

    @Test
    @DisplayName("When there are cars with given EngineType")
    void test3() {
        List<Car> expectedCars = List.of(AUDI);
        assertThat(carService.findCarsWithEngineType(EngineType.DIESEL))
                .containsExactlyElementsOf(expectedCars);
    }

}
