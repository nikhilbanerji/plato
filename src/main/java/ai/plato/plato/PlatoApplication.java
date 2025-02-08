package ai.plato.plato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlatoApplication {

	private static final Logger log = LoggerFactory.getLogger(PlatoApplication.class);

	public static void main(String[] args) {
		log.info("Starting PlatoApplication...");
		SpringApplication.run(PlatoApplication.class, args);
	}

}
