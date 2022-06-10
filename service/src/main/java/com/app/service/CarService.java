package com.app.service;

import com.app.persistence.converter.CarsJsonConverter;
import com.app.persistence.model.car.Car;
import com.app.persistence.model.carbody.CarBodyType;
import com.app.persistence.model.engine.EngineType;
import com.app.persistence.model.wheel.TyreType;
import com.app.persistence.validator.CarValidator;
import com.app.persistence.validator.Validator;
import com.app.service.exception.CarServiceException;
import com.app.service.type.Sort;
import com.app.service.type.Statistics;
import com.app.service.type.StatisticsType;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.persistence.model.car.CarUtils.*;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class CarService {

    private final List<Car> cars;

    public CarService(String filename) {
        this.cars = init(filename);
    }

    private static List<Car> init(String filename) {
        CarValidator carValidator = new CarValidator();
        return new CarsJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarServiceException("Cannot read data from json %s%n".formatted(filename)))
                .stream()
                .peek(car -> Validator.validate(car, carValidator))
                .toList();
    }

    /*
        Metoda zwraca kolekcję samochodów posortowaną według kryterium
        podanego jako argument. Metoda powinna umożliwiać sortowanie
        według ilości komponentów, mocy silnika oraz rozmiaru opony.
        Dodatkowo metoda powinna umożliwiać sortowanie rosnąco oraz
        malejąco.
    */
    public List<Car> sort(Sort sort, boolean descending) {
        if (sort == null) {
            throw new CarServiceException("Sort object is null");
        }
        List<Car> sortedCars = switch (sort) {
            case COMPONENTS_NUMBER -> cars.stream().sorted(descending ? compareByComponentsNumberDesc : compareByComponentsNumberAsc).toList();
            case WHEEL_SIZE -> cars.stream().sorted(descending ? compareByWheelSizeDesc : compareByWheelSizeAsc).toList();
            case ENGINE_POWER -> cars.stream().sorted(descending ? compareByEnginePowerDesc : compareByEnginePowerAsc).toList();
        };
        return sortedCars;
    }

    /*
        Metoda zwraca kolekcję samochodów o określonym rodzaju nadwozia
        przekazanym jako argument (CarBodyType) oraz o cenie z
        przedziału <a, b>, gdzie a oraz b to kolejne argumenty metody.
     */
    public List<Car> findCarsWithBodyTypeAndPriceInRange(CarBodyType carBodyType, BigDecimal minPrice, BigDecimal maxPrice) {
        if (carBodyType == null) {
            throw new CarServiceException("CarBodyType object is null");
        }
        if (minPrice == null || maxPrice == null) {
            throw new CarServiceException("BigDecimal object is null");
        }
        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new CarServiceException("Given price must be positive");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new CarServiceException("minPrice cannot be greater than maxPrice");
        }
        return cars.stream()
                .filter(car -> car.hasBodyType(carBodyType) && car.hasPriceInRange(minPrice, maxPrice))
                .toList();
    }

    /*
        Metoda zwraca posortowaną alfabetycznie kolekcję modeli
        samochodów, które posiadają typ silnika (EngineType) przekazany
        jako argument metody.
    */
    public List<Car> findCarsWithEngineType(EngineType engineType) {
        if (engineType == null) {
            throw new CarServiceException("EngineType object is null");
        }
        return cars.stream()
                .filter(car -> car.hasEngineType(engineType))
                .sorted(comparing(Car::getModel))
                .toList();
    }

    /*
        Metoda zwraca dane statystyczne dla podanej jako argument
        wielkości. Dopuszczalne wielkości to cena, przebieg oraz moc
        silnika. Dane statystyczne powinny zawierać wartość
        najmniejszą, wartość największą oraz wartość średnią.
     */
    public Statistics getStatistics(StatisticsType statisticsType) {
        if (statisticsType == null) {
            throw new CarServiceException("StatisticsType object is null");
        }
        return switch (statisticsType) {
            case PRICE -> Statistics.toStatistics(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)));
            case ENGINE_POWER -> Statistics.toStatistics(cars.stream().collect(Collectors.summarizingDouble(Car::getEnginePower)));
            case MILEAGE -> Statistics.toStatistics(cars.stream().collect(Collectors.summarizingDouble(Car::getMileage)));
        };
    }

    /*
        Metoda zwraca mapę, w której kluczem jest obiekt klasy Car,
        natomiast wartością jest liczba kilometrów, które samochód
        przejechał. Pary w mapie posortowane są malejąco według
        wartości.
     */
    public Map<Car, Integer> getCarsWithMileages() {
        return cars.stream()
                .collect(toMap(Function.identity(), Car::getMileage))
                .entrySet()
                .stream()
                .sorted(comparingInt(Map.Entry::getValue))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum, LinkedHashMap::new));
    }

    /*
        Metoda zwraca mapę, w której kluczem jest rodzaj opony
        (TyreType), a wartością lista samochodów o takim typie opony.
        Mapa posortowana jest malejąco po ilości elementów w kolekcji.
     */
    public Map<TyreType, List<Car>> getCarsGroupedByTyreType() {
        return cars.stream()
                .collect(groupingBy(Car::getTyreType))
                .entrySet()
                .stream()
                .sorted(comparingInt(entry -> entry.getValue().size()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (l1, l2) -> l1, LinkedHashMap::new));
    }

    /*
        Metoda zwraca kolekcję samochodów, które posiadają wszystkie
        komponenty z kolekcji przekazanej jako argument. Kolekcja
        posortowana jest alfabetycznie według nazwy modelu samochodu.
     */
    public List<Car> findCarsWithComponents(List<String> components) {
        if (components == null) {
            throw new CarServiceException("List with components is null");
        }
        return cars.stream()
                .filter(car -> car.containsComponents(components))
                .sorted(comparing(Car::getModel))
                .toList();
    }

}
