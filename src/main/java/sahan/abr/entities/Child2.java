package sahan.abr.entities;

import lombok.Data;

import java.util.HashMap;


@Data
public class Child2 {

    private Integer id;
    private String surname;
    private String name;
    private String shortName;
    private String middleName;
    private String gender;
    private String dateOfBirth;
    private String[] certificateValidityDate;
    private String note;

    private Subscription subscription;
    private int numberOfLessonsRemaining;
    private String[] subscriptionValidity;

    private boolean actionSubscription;
    private String causeOfFreezing;
    private String[] subscriptionValidityFreezing;

    private Employee trainer;

    private HashMap<Integer, DateVisitCustomer> timetable;


    public Child2() {
    }

    public Child2(Integer id, String surname, String name,
                  String shortName, String middleName,
                  String gender, String dateOfBirth,
                  String[] certificateValidityDate, String note,
                  Subscription subscription, int numberOfLessonsRemaining,
                  String[] subscriptionValidity, boolean actionSubscription,
                  String causeOfFreezing, String[] subscriptionValidityFreezing) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.shortName = shortName;
        this.middleName = middleName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.certificateValidityDate = certificateValidityDate;
        this.note = note;
        this.subscription = subscription;
        this.numberOfLessonsRemaining = numberOfLessonsRemaining;
        this.subscriptionValidity = subscriptionValidity;
        this.actionSubscription = actionSubscription;
        this.causeOfFreezing = causeOfFreezing;
        this.subscriptionValidityFreezing = subscriptionValidityFreezing;
    }
}
