package de.kemistheiss.fullfront;

import de.kemistheiss.fullfront.person.PersonRepository;
import de.kemistheiss.fullfront.person.PersonService;
import de.kemistheiss.fullfront.person.model.Person;
import de.kemistheiss.fullfront.shame.model.ShamePoint;
import de.kemistheiss.fullfront.shame.model.ShameRequest;
import de.kemistheiss.fullfront.shame.model.ShameResponse;
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

    public ShameResponse postShamePoint(ShameRequest shameRequest) {
        Optional<Person> person = personRepository.findByName(shameRequest.getPersonName());

        return addShamePointToPersonAndSave(person, shameRequest);
    }

    private ShameResponse addShamePointToPersonAndSave(Optional<Person> optionalPerson, ShameRequest shameRequest) {
        ShamePoint newShamePoint = createShamePointFromRequest(shameRequest);
        Person person = getOptionalOrCreateNewPerson(optionalPerson, shameRequest);

        saveShamePointToPerson(person, newShamePoint);

        return new ShameResponse(person.getName(), newShamePoint.getDate());
    }

    private Person getOptionalOrCreateNewPerson(Optional<Person> optionalPerson, ShameRequest shameRequest) {
        return optionalPerson.orElseGet(() -> createNewPerson(shameRequest.getPersonName()));
    }

    private ShamePoint createShamePointFromRequest(ShameRequest shameRequest) {
        ShamePoint shamePoint = new ShamePoint();
        shamePoint.setDate(LocalDate.now());

        return shamePoint;
    }

    private Person createNewPerson(String personName) {
        Person newPerson = new Person();
        newPerson.setName(personName);
        newPerson.setShamePoints(new ArrayList<>());

        return newPerson;
    }

    private void saveShamePointToPerson(Person person, ShamePoint shamePoint) {
        person.getShamePoints().add(shamePoint);
        personRepository.save(person);
    }

}
