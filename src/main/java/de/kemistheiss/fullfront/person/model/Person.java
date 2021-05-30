package de.kemistheiss.fullfront.person.model;

import de.kemistheiss.fullfront.shame.model.ShamePoint;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private String id;

    @NonNull
    private String name;

    private List<ShamePoint> shamePoints;

}
