package pt.brunofilipe.citytemp.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Temperature {
    UUID id;
    Float min;
    Float max;
}
