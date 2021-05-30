package de.kemistheiss.fullfront.shame.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShamePoint {

    @NonNull
    private LocalDate date;

}
