package com.app.persistence.validator;

import com.app.persistence.model.car.Car;

import java.util.HashMap;
import java.util.Map;

public class CarValidator implements Validator<Car> {

    private final Map<String, String> errors;

    public CarValidator() {
        this.errors = new HashMap<>();
    }

    @Override
    public Map<String, String> validate(Car car) {
        if (car == null) {
            errors.put("car", "object is null");
            return errors;
        }
        errors.putAll(new EngineValidator().validate(car.getEngine()));
        errors.putAll(new WheelValidator().validate(car.getWheel()));
        errors.putAll(new CarBodyValidator().validate(car.getCarBody()));
        return errors;
    }

}
