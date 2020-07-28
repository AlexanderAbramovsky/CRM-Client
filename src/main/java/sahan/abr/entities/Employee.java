package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String surname;
    private String name;
    private String middleName;
    private String position;
    private String phoneNumber;


    public Employee(String surname, String name, String middleName, String position, String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }
}
