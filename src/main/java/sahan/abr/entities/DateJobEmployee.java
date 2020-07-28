package sahan.abr.entities;

public class DateJobEmployee  {
    private String startTime;
    private String endTime;
    private String date;
    private String pool;

    public DateJobEmployee(String startTime, String endTime, String date, String pool) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.pool = pool;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }
}
