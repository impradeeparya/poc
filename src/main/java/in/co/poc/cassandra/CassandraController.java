package in.co.poc.cassandra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/poc/cassandra")
public class CassandraController {

  @Autowired
  private PersonRepository personRepository;

  @PostMapping(value = "/")
  public ResponseEntity create(@RequestBody PersonInfo personInfo) {

    LocalDateTime dob = LocalDateTime.ofInstant(Instant.ofEpochMilli(personInfo.getDob()),
        TimeZone.getDefault().toZoneId());
    PersonKey personKey = new PersonKey(personInfo.getFirstName(), dob, UUID.randomUUID());

    Person person = new Person(personKey, personInfo.getLastName(), personInfo.getSalary());
    personRepository.save(person);

    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(value = "/{firstName}")
  public PersonInfo fetchPersonInfo(@PathVariable String firstName) {
    Person person = personRepository.findByKeyFirstName(firstName).get(0);
    return new PersonInfo(person);
  }
}
