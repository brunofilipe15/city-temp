package pt.brunofilipe.citytemp.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    Float temp;

    @NotNull
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    City city;
}
