package com.app.api;

import com.app.persistence.model.carbody.CarBodyType;
import com.app.persistence.model.engine.EngineType;
import com.app.service.CarService;
import com.app.service.type.Sort;
import com.app.service.type.StatisticsType;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private static final String INPUT_FILE_PATH = "data\\cars.json";

    public static void main(String[] args) {
        CarService carService = new CarService(INPUT_FILE_PATH);
        System.out.println(carService.getCarsWithMileages());
        System.out.println(carService.getCarsGroupedByTyreType());
        System.out.println(carService.findCarsWithComponents(List.of("ABS", "AIR CONDITIONING")));
        System.out.println(carService.findCarsWithEngineType(EngineType.DIESEL));
        System.out.println(carService.findCarsWithBodyTypeAndPriceInRange(CarBodyType.HATCHBACK,
                new BigDecimal("120"),
                new BigDecimal("140")));
        System.out.println(carService.sort(Sort.WHEEL_SIZE, false));
        System.out.println(carService.sort(Sort.COMPONENTS_NUMBER, false));
        System.out.println(carService.sort(Sort.ENGINE_POWER, false));
        System.out.println(carService.getStatistics(StatisticsType.MILEAGE));
        System.out.println(carService.getStatistics(StatisticsType.PRICE));
        System.out.println(carService.getStatistics(StatisticsType.ENGINE_POWER));
    }

}
