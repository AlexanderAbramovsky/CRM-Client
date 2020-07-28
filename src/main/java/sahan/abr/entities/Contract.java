package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contract {
    private Integer id;
    private Integer idClient;
    private String number;
    private String date;

    public Contract(Integer idClient, String number, String date) {
        this.idClient = idClient;
        this.number = number;
        this.date = date;
    }
}
