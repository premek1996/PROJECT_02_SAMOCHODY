package com.app.persistence.validator;

import com.app.persistence.model.wheel.Wheel;

import java.util.HashMap;
import java.util.Map;

import static com.app.persistence.validator.Validator.containsNotOnlyWhitespacesOrUpperCaseCharacters;

public class WheelValidator implements Validator<Wheel> {

    private final Map<String, String> errors;

    public WheelValidator() {
        this.errors = new HashMap<>();
    }

    @Override
    public Map<String, String> validate(Wheel wheel) {
        if (wheel == null) {
            errors.put("wheel", "object is null");
            return errors;
        }
        if (wheel.getSize() < 0) {
            errors.put("size", "has to be >= 0");
        }
        validateModel(wheel.getModel());
        return errors;
    }

    private void validateModel(String model) {
        if (model == null) {
            errors.put("model", "object is null");
        } else if (containsNotOnlyWhitespacesOrUpperCaseCharacters(model)) {
            errors.put("model", "doesn't match regex");
        }
    }

}
