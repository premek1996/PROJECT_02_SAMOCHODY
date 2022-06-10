package com.app.persistence.model.car;

import java.util.Comparator;

public interface CarUtils {
    Comparator<Car> compareByComponentsNumberAsc = Comparator.comparing(Car::getComponentsNumber);
    Comparator<Car> compareByComponentsNumberDesc = Comparator.comparing(Car::getComponentsNumber, Comparator.reverseOrder());
    Comparator<Car> compareByWheelSizeAsc = Comparator.comparing(Car::getWheelSize);
    Comparator<Car> compareByWheelSizeDesc = Comparator.comparing(Car::getWheelSize, Comparator.reverseOrder());
    Comparator<Car> compareByEnginePowerAsc = Comparator.comparing(Car::getEnginePower);
    Comparator<Car> compareByEnginePowerDesc = Comparator.comparing(Car::getEnginePower, Comparator.reverseOrder());
}
