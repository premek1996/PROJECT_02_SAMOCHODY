package com.app.persistence.model.engine;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Engine {

    private final EngineType type;
    private final double power;

    public boolean hasType(EngineType engineType) {
        return type == engineType;
    }

}
