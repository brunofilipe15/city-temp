package pt.brunofilipe.citytemp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.brunofilipe.citytemp.model.City;
import pt.brunofilipe.citytemp.model.Temperature;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long> {
    List<Temperature> findAllByCityAndDateGreaterThanEqualAndDateLessThanEqual(City city, LocalDateTime startDate, LocalDateTime endDate);

    Optional<Temperature> findLastByCityAndDateLessThanEqualOrderByDate(City city, LocalDateTime date);
}
