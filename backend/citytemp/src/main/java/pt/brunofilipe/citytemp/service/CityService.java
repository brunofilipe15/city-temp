package pt.brunofilipe.citytemp.service;

import org.springframework.stereotype.Service;
import pt.brunofilipe.citytemp.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CityService {

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(City.builder().id(UUID.randomUUID()).name("Coimbra").build());
        cities.add(City.builder().id(UUID.randomUUID()).name("Aveiro").build());
        cities.add(City.builder().id(UUID.randomUUID()).name("Aveiro").build());
        cities.add(City.builder().id(UUID.randomUUID()).name("Guimarães").build());
        cities.add(City.builder().id(UUID.randomUUID()).name("Guarda").build());
        cities.add(City.builder().id(UUID.randomUUID()).name("Braga").build());
        return cities;
    }

}
