package com.app.service.extensions;

import com.app.service.CarService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


public class CarServiceExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        String filename = "C:\\Users\\jambop\\Desktop\\szkolenie\\projects\\PROJECT_02_SAMOCHODY\\service\\src\\test\\resources\\cars-test.json";
        return new CarService(filename);
    }

}
