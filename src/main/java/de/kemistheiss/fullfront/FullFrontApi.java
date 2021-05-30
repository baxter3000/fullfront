package de.kemistheiss.fullfront;

import de.kemistheiss.fullfront.shame.model.ShameRequest;
import de.kemistheiss.fullfront.shame.model.ShameResponse;
import de.kemistheiss.fullfront.person.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface FullFrontApi {

    @GetMapping
    List<Person> getAllPersons();

    @GetMapping("/{year}/{month}")
    List<Person> getAllPersonsWithShamePointsFromMonth(@PathVariable int year, @PathVariable int month);

    @PostMapping
    ShameResponse postShamePoint(@RequestBody ShameRequest shameRequest);
}
