package model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class User {

    private final String name;
    private final String password;
    private int ID;
}
