package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientNote {
    private Integer id;
    private Integer idClient;
    private String note;
    private String week;

    public ClientNote(Integer idClient, String note, String week) {
        this.idClient = idClient;
        this.note = note;
        this.week = week;
    }
}
