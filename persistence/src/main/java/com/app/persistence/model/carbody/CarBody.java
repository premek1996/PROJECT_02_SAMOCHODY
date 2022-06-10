package com.app.persistence.model.carbody;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CarBody {

    private final CarBodyColor color;
    private final CarBodyType type;
    private final List<String> components;

    public boolean containsComponents(List<String> components) {
        return this.components.containsAll(components);
    }

    public boolean hasBodyType(CarBodyType carBodyType) {
        return type == carBodyType;
    }

    public int getComponentsNumber() {
        return components.size();
    }

}
