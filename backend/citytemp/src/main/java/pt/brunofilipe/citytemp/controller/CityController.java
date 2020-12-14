package pt.brunofilipe.citytemp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.brunofilipe.citytemp.dto.CityDTO;
import pt.brunofilipe.citytemp.model.City;
import pt.brunofilipe.citytemp.service.CityService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<CityDTO> getAllCities() {
        List<CityDTO> citiesDTO = new ArrayList<>();
        List<City> cities = cityService.getAllCities();
        for (City city : cities) {
            citiesDTO.add(cityToCityDTO(city));
        }
        return citiesDTO;
    }

    private CityDTO cityToCityDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

}
