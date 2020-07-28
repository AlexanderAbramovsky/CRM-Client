package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionsReport {

    private Integer id;
    private Integer idClient;
    private Integer idEmployee;
    private String fioEmployee;
    private String fioClient;
    private String subscription;
    private String date;
    private Float money;
    private String note;

    public SubscriptionsReport(Integer idClient, Integer idEmployee, String fioEmployee, String fioClient, String subscription, String date, Float money, String note) {
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.fioEmployee = fioEmployee;
        this.fioClient = fioClient;
        this.subscription = subscription;
        this.date = date;
        this.money = money;
        this.note = note;
    }
}
