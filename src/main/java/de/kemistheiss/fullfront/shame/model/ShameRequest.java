package de.kemistheiss.fullfront.shame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShameRequest {

    @NonNull
    private String personName;

}
