package pt.brunofilipe.citytemp.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    UUID id;
    String name;
}
