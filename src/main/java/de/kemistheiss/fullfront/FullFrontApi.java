package de.kemistheiss.fullfront;

import de.kemistheiss.fullfront.model.Request;
import de.kemistheiss.fullfront.model.Response;
import de.kemistheiss.fullfront.person.model.Person;
import de.kemistheiss.fullfront.shame.model.ShamePoint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface FullFrontApi {

    @GetMapping
    List<Person> getAllPersons();

    @GetMapping("/{year}/{month}")
    List<Person> getAllPersonsWithShamePointsFromMonth(@PathVariable int year, @PathVariable int month);

    @PostMapping
    Response postShamePoint(@RequestBody Request request);
}
