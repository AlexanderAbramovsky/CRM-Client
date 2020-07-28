package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Child {
    private Integer id;
    private Integer idClient;
    private Integer idReference;
    private String surname;
    private String name;
    private String middleName;
    private Gender gender;
    private String dateOfBirth;
    private String note;
    private String referenceSTime;
    private String referenceETime;
    private String pairLessons;

    public Child(Integer idClient, Integer idReference, String surname, String name, String middleName, Gender gender, String dateOfBirth, String note, String referenceSTime, String referenceETime, String pairLessons) {
        this.idClient = idClient;
        this.idReference = idReference;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.note = note;
        this.pairLessons = pairLessons;
        this.referenceSTime = referenceSTime;
        this.referenceETime = referenceETime;
    }
}