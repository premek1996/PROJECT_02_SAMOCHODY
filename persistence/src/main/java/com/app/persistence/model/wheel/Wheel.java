package com.app.persistence.model.wheel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Wheel {

    private final String model;
    private final int size;
    private final TyreType type;

}
