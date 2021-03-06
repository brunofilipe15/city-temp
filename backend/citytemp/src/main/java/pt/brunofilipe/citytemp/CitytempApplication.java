package pt.brunofilipe.citytemp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.brunofilipe.citytemp.dao.CityRepository;
import pt.brunofilipe.citytemp.dao.TemperatureRepository;
import pt.brunofilipe.citytemp.model.City;
import pt.brunofilipe.citytemp.model.Temperature;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CitytempApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitytempApplication.class, args);
	}

	@Bean
	public CommandLineRunner citiesData(CityRepository cityRepository, TemperatureRepository temperatureRepository) {
		return args -> {
			City coimbra = City.builder().name("Coimbra").build();
			cityRepository.save(coimbra);
			createTemperatures(coimbra, temperatureRepository);

			City braga = City.builder().name("Braga").build();
			cityRepository.save(braga);
			createTemperatures(braga, temperatureRepository);

			City mirandela = City.builder().name("Mirandela").build();
			cityRepository.save(mirandela);
			createTemperatures(mirandela, temperatureRepository);

			City guarda = City.builder().name("Guarda").build();
			cityRepository.save(guarda);
			createTemperatures(guarda, temperatureRepository);

			City agueda = City.builder().name("Águeda").build();
			cityRepository.save(agueda);
			createTemperatures(agueda, temperatureRepository);

		};
	}

	private void createTemperatures(City city, TemperatureRepository temperatureRepository) {
		for(int dias = 0; dias < 25; dias++) { // Previsão para 25 dias
			for(int horas = 0; horas < 24; horas++) {
				temperatureRepository.save(Temperature.builder().city(city)
					.date(LocalDateTime.now(ZoneOffset.UTC).plusDays(dias).plusHours(horas))
					.temp(40 + new Random().nextFloat() * (40 - 65))
					.build());
			}
		}
	}
}
