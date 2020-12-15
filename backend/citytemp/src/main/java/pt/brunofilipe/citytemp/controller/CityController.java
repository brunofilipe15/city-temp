package pt.brunofilipe.citytemp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.brunofilipe.citytemp.dao.CityRepository;
import pt.brunofilipe.citytemp.dto.CityDTO;
import pt.brunofilipe.citytemp.model.City;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<CityDTO> getAllCities() {
        List<CityDTO> citiesDTO = new ArrayList<>();
        for (City city : cityRepository.findAll()) {
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
