package spring.database.database_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.database.database_demo.entity.Person;
import spring.database.database_demo.jpa.PersonJpaRepository;
import spring.database.database_demo.jpa.PersonSpringDataRepository;

import java.util.Date;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger((this.getClass()));

	@Autowired
	PersonSpringDataRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("User id 10001 -> {}", repository.findById(10001));
		logger.info("insert 10004 -> {}",
				repository.save(new Person("blah", "blah", new Date())));
		logger.info("updating 10003 -> {}",
				repository.save(new Person(10003, "blah", "blah", new Date())));
		repository.deleteById(10002);
		logger.info("All users in dao -> {}", repository.findAll());

	}
}
