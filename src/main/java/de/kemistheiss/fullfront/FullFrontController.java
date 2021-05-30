package de.kemistheiss.fullfront;

import de.kemistheiss.fullfront.model.Request;
import de.kemistheiss.fullfront.model.Response;
import de.kemistheiss.fullfront.person.model.Person;
import de.kemistheiss.fullfront.shame.model.ShamePoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FullFrontController implements FullFrontApi {

    private final FullFrontService fullFrontService;

    @Autowired
    public FullFrontController(FullFrontService fullFrontService) {
        this.fullFrontService = fullFrontService;
    }

    @Override
    public List<Person> getAllPersons() {
        return fullFrontService.getAllPersons();
    }

    @Override
    public List<Person> getAllPersonsWithShamePointsFromMonth(int year, int month) {
        return fullFrontService.getAllPersonsWithShamePointsFromMonth(year, month);
    }

    @Override
    public Response postShamePoint(Request request) {
        return fullFrontService.postShamePoint(request);
    }

}
