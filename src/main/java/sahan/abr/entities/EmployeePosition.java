package sahan.abr.entities;

public enum EmployeePosition {
    TRAINER("������"),
    ADMINISTRATOR("�������������");

    String val;

    public String getVal() {
        return val;
    }

    EmployeePosition(String role) {
        val = role;
    }
}
