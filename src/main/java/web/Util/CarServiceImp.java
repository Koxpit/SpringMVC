package web.Util;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarServiceImp implements CarService {
    private final List<Car> cars;

    public CarServiceImp() {
        cars = new ArrayList<>();
        cars.add(new Car("MBW", 1, 1000));
        cars.add(new Car("Mercedes", 12, 1200));
        cars.add(new Car("Lexus", 8, 800));
        cars.add(new Car("Priora", 5, 300));
        cars.add(new Car("Lada", 16, 100));
    }

    @Override
    public List<Car> getCars(int count) {
        return cars.size() > count && count <= 5 ? (ArrayList<Car>) cars.stream()
                .map(x -> new Car(x.getModel(), x.getSeries(), x.getPrice())).limit(count)
                .collect(Collectors.toList()) : cars;
    }
}
