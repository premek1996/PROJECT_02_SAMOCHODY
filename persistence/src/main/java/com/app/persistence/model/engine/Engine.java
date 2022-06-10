package com.app.persistence.model.engine;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Engine {

    private final EngineType type;
    private final double power;

    public boolean hasType(EngineType engineType) {
        return type == engineType;
    }

}
