package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passport {
    private Integer id;
    private Integer idClient;
    private int series;
    private int number;
    private String date;
    private String issuedBy;
    private String address;

    public Passport(Integer idClient, int series, int number, String date, String issuedBy, String address) {
        this.idClient = idClient;
        this.series = series;
        this.number = number;
        this.date = date;
        this.issuedBy = issuedBy;
        this.address = address;
    }
}
