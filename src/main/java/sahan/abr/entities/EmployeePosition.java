package sahan.abr.entities;

public enum EmployeePosition {
    TRAINER("Тренер"),
    ADMINISTRATOR("Администратор");

    String val;

    public String getVal() {
        return val;
    }

    EmployeePosition(String role) {
        val = role;
    }
}
