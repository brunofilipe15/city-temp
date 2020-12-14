package pt.brunofilipe.citytemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.brunofilipe.citytemp.dao.CityRepository;
import pt.brunofilipe.citytemp.model.City;

@SpringBootApplication
public class CitytempApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitytempApplication.class, args);
	}

	@Bean
	public CommandLineRunner citiesData(CityRepository cityRepository) {
		return args -> {
			cityRepository.save(City.builder().name("Coimbra").build());
			cityRepository.save(City.builder().name("Braga").build());
			cityRepository.save(City.builder().name("Mirandela").build());
			cityRepository.save(City.builder().name("Guarda").build());
			cityRepository.save(City.builder().name("Sintra").build());
			cityRepository.save(City.builder().name("Aveiro").build());
			cityRepository.save(City.builder().name("Almada").build());
			cityRepository.save(City.builder().name("Águeda").build());
			cityRepository.save(City.builder().name("Gondomar").build());
		};
	}

}
