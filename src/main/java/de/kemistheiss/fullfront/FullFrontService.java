package de.kemistheiss.fullfront;

import de.kemistheiss.fullfront.model.Request;
import de.kemistheiss.fullfront.model.Response;
import de.kemistheiss.fullfront.person.PersonRepository;
import de.kemistheiss.fullfront.person.PersonService;
import de.kemistheiss.fullfront.person.model.Person;
import de.kemistheiss.fullfront.shame.model.ShamePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FullFrontService {

    private final PersonRepository personRepository;

    private final PersonService personService;

    @Autowired
    public FullFrontService(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getAllPersonsWithShamePointsFromMonth(int year, int month) {
        List<Person> personList = personRepository.findAll();
        return personService.filterShamePoints(personList, year, month);
    }

    public Response postShamePoint(Request request) {
        Optional<Person> person = personRepository.findByName(request.getPersonName());

        return addShamePointToPersonAndSave(person, request);
    }

    private Response addShamePointToPersonAndSave(Optional<Person> optionalPerson, Request request) {
        ShamePoint newShamePoint = createShamePointFromRequest(request);
        Person person = new Person();

        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
        } else {
           createNewPerson(person, request.getPersonName());
        }

        saveShamePointToPerson(person, newShamePoint);

        return new Response(person.getName(), newShamePoint.getDate());
    }

    private ShamePoint createShamePointFromRequest(Request request) {
        ShamePoint shamePoint = new ShamePoint();
        shamePoint.setDate(LocalDate.now());

        return shamePoint;
    }

    private void createNewPerson(Person person, String personName) {
        person.setName(personName);
        person.setShamePoints(new ArrayList<>());
    }

    private void saveShamePointToPerson(Person person, ShamePoint shamePoint) {
        person.getShamePoints().add(shamePoint);
        personRepository.save(person);
    }

}
