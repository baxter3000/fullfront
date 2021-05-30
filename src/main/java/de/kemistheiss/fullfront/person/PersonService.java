package de.kemistheiss.fullfront.person;

import de.kemistheiss.fullfront.person.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class PersonService {

    public List<Person> filterShamePoints(List<Person> persons, int year, int month) {
       return persons.stream()
                .map(person -> filterShamePoints(person, year, month))
                .collect(Collectors.toList());
    }

    public Person filterShamePoints(Person person, int year, int month) {
        person.setShamePoints(
                person.getShamePoints()
                .stream()
                .filter(shamePoint -> isInRespectiveYearAndMonth(shamePoint.getDate(), year, month))
                .collect(Collectors.toList()));

        return person;
    }

    private boolean isInRespectiveYearAndMonth(LocalDate date, int year, int month) {
        boolean isSameMonth = date.getMonth().getValue() == month;
        boolean isSameYear = date.getYear() == year;

        return isSameMonth && isSameYear;
    }
}
