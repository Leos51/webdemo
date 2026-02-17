package training.afpa.cda24060.webdemo.model;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Person {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;

}
