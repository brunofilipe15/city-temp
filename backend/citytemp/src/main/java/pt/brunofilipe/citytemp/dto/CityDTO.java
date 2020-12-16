package pt.brunofilipe.citytemp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    Long id;
    String name;
}
