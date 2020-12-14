package pt.brunofilipe.citytemp.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @NotNull
    Float minTemp;

    @NotNull
    Float maxTemp;

    @NotNull
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    City city;
}
