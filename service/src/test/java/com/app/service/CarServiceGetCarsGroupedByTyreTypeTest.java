package com.app.service;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.wheel.TyreType;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.app.service.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetCarsGroupedByTyreTypeTest {

    private final CarService carService;

    @Test
    @DisplayName("When cars are available")
    void test1() {
        Map<TyreType, List<Car>> expectedCarsCarsGroupedByTyreType = Map.of(TyreType.WINTER, List.of(BMW), TyreType.SUMMER, List.of(AUDI, MAZDA));
        assertThat(carService.getCarsGroupedByTyreType())
                .containsExactlyInAnyOrderEntriesOf(expectedCarsCarsGroupedByTyreType);
    }

}
