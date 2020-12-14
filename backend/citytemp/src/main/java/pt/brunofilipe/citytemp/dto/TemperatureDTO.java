package pt.brunofilipe.citytemp.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureDTO {
    Long id;
    Float minTempCelsius;
    Float maxTempCelsius;
    Float minTempFahrenheit;
    Float maxTempFahrenheit;
    LocalDateTime localDate;
}
