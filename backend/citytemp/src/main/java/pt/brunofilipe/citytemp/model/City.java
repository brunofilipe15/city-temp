package pt.brunofilipe.citytemp.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    UUID id;
    String name;
    List<Temperature> temperatures;
}
