package pt.brunofilipe.citytemp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.brunofilipe.citytemp.model.City;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cities")
public class TemperatureController {

    @GetMapping(value = " {cityId}/temperatures")
    public List<City> getAllCities(@RequestParam UUID cityId, @RequestParam @Min(1) @Max(5) Long numbersOfDays) {
        return null;
    }

}
