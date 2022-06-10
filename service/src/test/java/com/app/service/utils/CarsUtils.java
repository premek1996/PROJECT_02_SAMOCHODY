package com.app.service.utils;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.carbody.CarBody;
import com.app.persistence.model.carbody.CarBodyColor;
import com.app.persistence.model.carbody.CarBodyType;
import com.app.persistence.model.engine.Engine;
import com.app.persistence.model.engine.EngineType;
import com.app.persistence.model.wheel.TyreType;
import com.app.persistence.model.wheel.Wheel;

import java.math.BigDecimal;
import java.util.List;

public interface CarsUtils {

    Car AUDI = Car
            .builder()
            .model("AUDI")
            .price(BigDecimal.valueOf(120))
            .mileage(12000)
            .engine(Engine.builder()
                    .type(EngineType.DIESEL)
                    .power(220)
                    .build())
            .carBody(CarBody.builder()
                    .color(CarBodyColor.BLACK)
                    .type(CarBodyType.HATCHBACK)
                    .components(List.of("ALLOY WHEELS"))
                    .build())
            .wheel(Wheel.builder()
                    .type(TyreType.SUMMER)
                    .model("PIRELLI")
                    .size(18)
                    .build())
            .build();

    Car BMW = Car
            .builder()
            .model("BMW")
            .price(BigDecimal.valueOf(170))
            .mileage(13000)
            .engine(Engine.builder()
                    .type(EngineType.GASOLINE)
                    .power(240)
                    .build())
            .carBody(CarBody.builder()
                    .color(CarBodyColor.SILVER)
                    .type(CarBodyType.SEDAN)
                    .components(List.of("ABS"))
                    .build())
            .wheel(Wheel.builder()
                    .type(TyreType.WINTER)
                    .model("PIRELLI")
                    .size(28)
                    .build())
            .build();

    Car MAZDA = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(140))
            .mileage(15000)
            .engine(Engine.builder()
                    .type(EngineType.GASOLINE)
                    .power(200)
                    .build())
            .carBody(CarBody.builder()
                    .color(CarBodyColor.SILVER)
                    .type(CarBodyType.SEDAN)
                    .components(List.of("BLUETOOTH"))
                    .build())
            .wheel(Wheel.builder()
                    .type(TyreType.SUMMER)
                    .model("PIRELLI")
                    .size(22)
                    .build())
            .build();

}
