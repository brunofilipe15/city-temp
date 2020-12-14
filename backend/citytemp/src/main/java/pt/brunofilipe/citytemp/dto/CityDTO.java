package pt.brunofilipe.citytemp.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    Long id;
    String name;
}
