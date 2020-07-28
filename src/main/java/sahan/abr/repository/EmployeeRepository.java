package sahan.abr.repository;

import sahan.abr.entities.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements CRUDRepository<Employee> {

    private final static String INSERT = "INSERT INTO EMPLOYEE (SURNAME, NAME, MIDDLE_NAME, POSITION, PHONE_NUMBER) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE EMPLOYEE SET SURNAME = ?, NAME = ?, MIDDLE_NAME = ?, POSITION = ?, PHONE_NUMBER = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM EMPLOYEE WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM EMPLOYEE";
    private final static String SELECT_BY_ID = "SELECT * FROM EMPLOYEE WHERE ID = ?";

    public static Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public Employee getById(Integer id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idEmployee = result.getInt("ID");
            String surname = result.getString("SURNAME");
            String name = result.getString("NAME");
            String middleName = result.getString("MIDDLE_NAME");
            String position = result.getString("POSITION");
            String phoneNumber = result.getString("PHONE_NUMBER");
            return new Employee(idEmployee, surname, name, middleName, position, phoneNumber);
        }

        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<Employee> employees = new ArrayList<>();

        while (result.next()) {
            int idEmployee = result.getInt("ID");
            String surname = result.getString("SURNAME");
            String name = result.getString("NAME");
            String middleName = result.getString("MIDDLE_NAME");
            String position = result.getString("POSITION");
            String phoneNumber = result.getString("PHONE_NUMBER");
            employees.add(new Employee(idEmployee, surname, name, middleName, position, phoneNumber));
        }

        return employees;
    }

    @Override
    public int save(Employee data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, data.getSurname());
        statement.setString(2, data.getName());
        statement.setString(3, data.getMiddleName());
        statement.setString(4, data.getPosition());
        statement.setString(5, data.getPhoneNumber());
        return executeId(statement);
    }

    @Override
    public boolean update(Employee data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, data.getSurname());
        statement.setString(2, data.getName());
        statement.setString(3, data.getMiddleName());
        statement.setString(4, data.getPosition());
        statement.setString(5, data.getPhoneNumber());
        statement.setInt(6, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}
