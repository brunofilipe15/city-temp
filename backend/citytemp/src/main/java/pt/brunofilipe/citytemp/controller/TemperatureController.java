package pt.brunofilipe.citytemp.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.brunofilipe.citytemp.dao.CityRepository;
import pt.brunofilipe.citytemp.dao.TemperatureRepository;
import pt.brunofilipe.citytemp.dto.CityDTO;
import pt.brunofilipe.citytemp.dto.TemperatureDTO;
import pt.brunofilipe.citytemp.model.City;
import pt.brunofilipe.citytemp.model.Temperature;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("city")
@CrossOrigin(origins = "http://localhost:4200")
public class TemperatureController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @GetMapping(value = "/{cityId}/temperatures")
    public List<TemperatureDTO> getAllCities(@PathVariable Long cityId, @RequestParam @Min(1) @Max(5) Long numbersOfDays)
            throws NotFoundException {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new NotFoundException("City not found"));
        List<TemperatureDTO> temperatureDTOS = new ArrayList<>();
        LocalDateTime startDate = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime endDate = LocalDateTime.now(ZoneOffset.UTC).plusDays(numbersOfDays);
        for (Temperature temperature : temperatureRepository.findAllByCityAndDateGreaterThanEqualAndDateLessThanEqual(city, startDate, endDate)) {
            temperatureDTOS.add(temperatureToTemperatureDTO(temperature));
        }
        return temperatureDTOS;
    }

    private TemperatureDTO temperatureToTemperatureDTO(Temperature temperature) {
        return TemperatureDTO.builder()
                .id(temperature.getId())
                .maxTempCelsius(temperature.getMaxTemp())
                .minTempCelsius(temperature.getMinTemp())
                .minTempFahrenheit(((temperature.getMinTemp()*9)/5)+32)
                .maxTempFahrenheit(((temperature.getMaxTemp()*9)/5)+32)
                .localDate(temperature.getDate())
                .build();
    }

}
