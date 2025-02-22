package spring.database.database_demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.database.database_demo.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
           Person person = new Person();
           person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthdate(rs.getTimestamp("birthDate"));
           return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from person where id=?",
                new PersonRowMapper(),
                id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from person where id=?",
                id);
    }

    public int insert(Person person) {
        return jdbcTemplate.update(
                "insert into person (id, name, location, birthdate) VALUES(?, ?, ?, ?)",
                new Object[] {person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime())});
    }

    public int update(Person person) {
        return jdbcTemplate.update(
                "update person " +
                        "set name = ?, location = ?,birthdate = ? " +

                        "where id = ?" ,
                        new Object[] {person.getName(),
                                person.getLocation(),
                                new Timestamp(person.getBirthdate().getTime()),
                                person.getId()});
    }
}
