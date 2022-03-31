package com.app.persistence.validator;

import com.app.persistence.model.carbody.CarBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.persistence.validator.Validator.containNotOnlyWhitespacesOrUpperCaseCharacters;

public class CarBodyValidator implements Validator<CarBody> {

    private final Map<String, String> errors;

    public CarBodyValidator() {
        this.errors = new HashMap<>();
    }

    @Override
    public Map<String, String> validate(CarBody carBody) {
        if (carBody == null) {
            errors.put("carBody", "object is null");
            return errors;
        }
        validateComponents(carBody.getComponents());
        return errors;
    }

    private void validateComponents(List<String> components) {
        if (components == null) {
            errors.put("components", "object is null");
        } else if (containNotOnlyWhitespacesOrUpperCaseCharacters(components)) {
            errors.put("components", "doesn't match regex");
        }
    }

}
