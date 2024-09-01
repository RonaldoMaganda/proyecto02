package cursoSpringBoot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private int ID;
    private String name;
    private String username;
    private String password;
}
