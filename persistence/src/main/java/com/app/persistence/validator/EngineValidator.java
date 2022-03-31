package com.app.persistence.validator;

import com.app.persistence.model.engine.Engine;

import java.util.HashMap;
import java.util.Map;

public class EngineValidator implements Validator<Engine> {

    private final Map<String, String> errors;

    public EngineValidator() {
        this.errors = new HashMap<>();
    }

    @Override
    public Map<String, String> validate(Engine engine) {
        if (engine == null) {
            errors.put("engine", "object is null");
            return errors;
        }
        if (engine.getPower() < 0) {
            errors.put("power", "has to be >= 0");
        }
        return errors;
    }

}
