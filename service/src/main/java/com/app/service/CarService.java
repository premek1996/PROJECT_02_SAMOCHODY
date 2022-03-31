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
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            case COMPONENTS_NUMBER -> cars.stream().sorted(Comparator.comparingInt(Car::getComponentsNumber)).toList();
            case WHEEL_SIZE -> cars.stream().sorted(Comparator.comparingInt(Car::getWheelSize)).toList();
            default -> cars.stream().sorted(Comparator.comparingDouble(Car::getEnginePower)).toList();
        };
        if (descending) {
            Collections.reverse(sortedCars);
        }
        return sortedCars;
    }

    /*
        Metoda zwraca kolekcję samochodów o określonym rodzaju nadwozia
        przekazanym jako argument (CarBodyType) oraz o cenie z
        przedziału <a, b>, gdzie a oraz b to kolejne argumenty metody.
     */
    public List<Car> getCarsWithBodyTypeAndPriceInRange(CarBodyType carBodyType, BigDecimal minPrice, BigDecimal maxPrice) {
        return cars.stream()
                .filter(car -> car.hasBodyType(carBodyType))
                .filter(car -> car.hasPriceInRange(minPrice, maxPrice))
                .toList();
    }

    /*
        Metoda zwraca posortowaną alfabetycznie kolekcję modeli
        samochodów, które posiadają typ silnika (EngineType) przekazany
        jako argument metody.
    */
    public List<Car> getCarsWithEngineType(EngineType engineType) {
        return cars.stream()
                .filter(car -> car.hasEngineType(engineType))
                .sorted(Comparator.comparing(Car::getModel))
                .toList();
    }

    /*
        Metoda zwraca dane statystyczne dla podanej jako argument
        wielkości. Dopuszczalne wielkości to cena, przebieg oraz moc
        silnika. Dane statystyczne powinny zawierać wartość
        najmniejszą, wartość największą oraz wartość średnią.
     */
    public Map<String, BigDecimal> getStatistics(Statistics statistics) {
        return switch (statistics) {
            case PRICE -> getStatistics(cars.stream().map(Car::getPrice).collect(Collectors2.summarizingBigDecimal(e -> e)));
            case ENGINE_POWER -> getStatistics(cars.stream().map(Car::getEnginePower).collect(Collectors.summarizingDouble(e -> e)));
            default -> getStatistics(cars.stream().map(Car::getMileage).collect(Collectors.summarizingDouble(e -> e)));
        };
    }

    private static Map<String, BigDecimal> getStatistics(BigDecimalSummaryStatistics bigDecimalSummaryStatistics) {
        Map<String, BigDecimal> statistics = new HashMap<>();
        statistics.put("MAX", bigDecimalSummaryStatistics.getMax());
        statistics.put("MIN", bigDecimalSummaryStatistics.getMin());
        statistics.put("AVERAGE", bigDecimalSummaryStatistics.getAverage());
        return statistics;
    }

    private static Map<String, BigDecimal> getStatistics(DoubleSummaryStatistics doubleSummaryStatistics) {
        Map<String, BigDecimal> statistics = new HashMap<>();
        statistics.put("MAX", BigDecimal.valueOf(doubleSummaryStatistics.getMax()));
        statistics.put("MIN", BigDecimal.valueOf(doubleSummaryStatistics.getMin()));
        statistics.put("AVERAGE", BigDecimal.valueOf(doubleSummaryStatistics.getAverage()));
        return statistics;
    }

    /*
        Metoda zwraca mapę, w której kluczem jest obiekt klasy Car,
        natomiast wartością jest liczba kilometrów, które samochód
        przejechał. Pary w mapie posortowane są malejąco według
        wartości.
     */
    public Map<Car, Integer> getCarsWithMileages() {
        return cars.stream()
                .collect(Collectors.toMap(Function.identity(), Car::getMileage))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum, LinkedHashMap::new));
    }

    /*
        Metoda zwraca mapę, w której kluczem jest rodzaj opony
        (TyreType), a wartością lista samochodów o takim typie opony.
        Mapa posortowana jest malejąco po ilości elementów w kolekcji.
     */
    public Map<TyreType, List<Car>> getCarsGroupedByTyreType() {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getTyreType))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (l1, l2) -> l1, LinkedHashMap::new));
    }

    /*
        Metoda zwraca kolekcję samochodów, które posiadają wszystkie
        komponenty z kolekcji przekazanej jako argument. Kolekcja
        posortowana jest alfabetycznie według nazwy modelu samochodu.
     */
    public List<Car> getCarsWhichContainsComponents(List<String> components) {
        return cars.stream()
                .filter(car -> car.containsComponents(components))
                .sorted(Comparator.comparing(Car::getModel))
                .toList();
    }

}
