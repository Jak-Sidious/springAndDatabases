package spring.database.database_demo.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.database.database_demo.entity.Person;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {
}
