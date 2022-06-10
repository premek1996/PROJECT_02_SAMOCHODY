package com.app.service;

import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import com.app.service.type.Statistics;
import com.app.service.type.StatisticsType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetStatisticsTest {

    private final CarService carService;

    @Test
    @DisplayName("When StatisticsType object is null")
    void test1() {
        assertThatThrownBy(() -> carService.getStatistics(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("StatisticsType object is null");
    }

    @Test
    @DisplayName("When StatisticsType is mileage")
    void test2() {
        Statistics expectedStatistics = new Statistics(new BigDecimal("12000.0"), new BigDecimal("13333.333333333334"), new BigDecimal("15000.0"));
        assertThat(carService.getStatistics(StatisticsType.MILEAGE))
                .isEqualTo(expectedStatistics);
    }

    @Test
    @DisplayName("When StatisticsType is price")
    void test3() {
        Statistics expectedStatistics = new Statistics(new BigDecimal("120"), new BigDecimal("143.3333333333333333333333333333333"), new BigDecimal("170"));
        assertThat(carService.getStatistics(StatisticsType.PRICE))
                .isEqualTo(expectedStatistics);
    }

    @Test
    @DisplayName("When StatisticsType is engine power")
    void test4() {
        Statistics expectedStatistics = new Statistics(new BigDecimal("200.0"), new BigDecimal("220.0"), new BigDecimal("240.0"));
        assertThat(carService.getStatistics(StatisticsType.ENGINE_POWER))
                .isEqualTo(expectedStatistics);
    }

}
