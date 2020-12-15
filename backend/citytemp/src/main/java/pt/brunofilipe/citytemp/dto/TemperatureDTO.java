package pt.brunofilipe.citytemp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureDTO {
    Long id;
    Float tempCelsius;
    Float tempFahrenheit;
    LocalDateTime localDate;
}
