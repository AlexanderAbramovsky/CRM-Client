package sahan.abr.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private Integer id;
    private ScheduleRole role;
    private int idRole;
    private String date;
    private String sTime;
    private String eTime;
    private String poll;
    private String was;

    public Schedule(ScheduleRole role, int idRole, String date, String sTime, String eTime, String poll, String was) {
        this.role = role;
        this.idRole = idRole;
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.poll = poll;
        this.was = was;
    }
}


