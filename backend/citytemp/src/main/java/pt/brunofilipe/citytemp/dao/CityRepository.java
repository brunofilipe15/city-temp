package pt.brunofilipe.citytemp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.brunofilipe.citytemp.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
