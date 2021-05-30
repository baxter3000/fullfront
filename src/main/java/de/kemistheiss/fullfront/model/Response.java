package de.kemistheiss.fullfront.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @NonNull
    private String personName;

    @NonNull
    private LocalDate date;

}
