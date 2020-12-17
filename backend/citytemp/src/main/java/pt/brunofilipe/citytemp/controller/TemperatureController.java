package pt.brunofilipe.citytemp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pt.brunofilipe.citytemp.dao.CityRepository;
import pt.brunofilipe.citytemp.dao.TemperatureRepository;
import pt.brunofilipe.citytemp.dto.TemperatureDTO;
import pt.brunofilipe.citytemp.model.City;
import pt.brunofilipe.citytemp.model.Temperature;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("city")
@CrossOrigin(origins = "http://35.223.247.138")
public class TemperatureController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @GetMapping(value = "/{cityId}/temperatures")
    public List<TemperatureDTO> getAllCities(@PathVariable Long cityId, @RequestParam Long numbersOfDays) {
        if (numbersOfDays < 1 || numbersOfDays > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid numbers of days");
        }
        City city = cityRepository.findById(cityId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,"City not found"));
        List<TemperatureDTO> temperatureDTOS = new ArrayList<>();
        LocalDateTime startDate = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime endDate = LocalDateTime.now(ZoneOffset.UTC).plusDays(numbersOfDays);
        for (Temperature temperature : temperatureRepository.findAllByCityAndDateGreaterThanEqualAndDateLessThanEqual(
                city, startDate, endDate)) {
            temperatureDTOS.add(temperatureToTemperatureDTO(temperature));
        }
        return temperatureDTOS;
    }

    @GetMapping(value = "/{cityId}/temperature-now")
    public TemperatureDTO getTemperatureInCities(@PathVariable Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,"City not found"));

        LocalDateTime date = LocalDateTime.now(ZoneOffset.UTC);
        Temperature temperature = temperatureRepository.findLastByCityAndDateLessThanEqualOrderByDate(city, date)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"City not found"));
        return temperatureToTemperatureDTO(temperature);
    }

    private TemperatureDTO temperatureToTemperatureDTO(Temperature temperature) {
        return TemperatureDTO.builder()
                .id(temperature.getId())
                .tempCelsius(temperature.getTemp())
                .tempFahrenheit(((temperature.getTemp()*9)/5)+32)
                .localDate(temperature.getDate())
                .build();
    }

}
