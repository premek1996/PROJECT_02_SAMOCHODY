package com.app.persistence.model.car;

import com.app.persistence.model.carbody.CarBody;
import com.app.persistence.model.carbody.CarBodyType;
import com.app.persistence.model.engine.Engine;
import com.app.persistence.model.engine.EngineType;
import com.app.persistence.model.wheel.TyreType;
import com.app.persistence.model.wheel.Wheel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@Builder
public class Car {

    private final String model;
    private final BigDecimal price;
    private final int mileage;
    private final Engine engine;
    private final CarBody carBody;
    private final Wheel wheel;

    public TyreType getTyreType() {
        return wheel.getType();
    }

    public boolean containsComponents(List<String> components) {
        return carBody.containsComponents(components);
    }

    public boolean hasEngineType(EngineType engineType) {
        return engine.hasType(engineType);
    }

    public boolean hasBodyType(CarBodyType carBodyType) {
        return carBody.hasBodyType(carBodyType);
    }

    public boolean hasPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return minPrice.compareTo(price) <= 0 && maxPrice.compareTo(price) >= 0;
    }

    public int getComponentsNumber() {
        return carBody.getComponentsNumber();
    }

    public int getWheelSize() {
        return wheel.getSize();
    }

    public double getEnginePower() {
        return engine.getPower();
    }

}
