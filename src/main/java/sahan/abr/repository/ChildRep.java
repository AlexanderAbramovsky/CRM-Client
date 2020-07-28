package sahan.abr.repository;

import sahan.abr.entities.Child;
import sahan.abr.entities.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChildRep implements CRUDRepository<Child> {

    private final static String INSERT = "INSERT INTO CHILD (ID_CLIENT, ID_REFERENCE, SURNAME, NAME, MIDDLE_NAME, GENDER, DATE_OF_BIRTH, NOTE, REFERENCE_S_TIME, REFERENCE_E_TIME, PAIR_LESSONS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE CHILD SET ID_CLIENT = ?, ID_REFERENCE = ?, SURNAME = ?, NAME = ?, MIDDLE_NAME = ?, GENDER = ?, DATE_OF_BIRTH = ?, NOTE = ?, REFERENCE_S_TIME = ?, REFERENCE_E_TIME = ?, PAIR_LESSONS = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM CHILD WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM CHILD";
    private final static String SELECT_BY_ID = "SELECT * FROM CHILD WHERE ID = ?";

    public static Connection connection;

    public ChildRep(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public Child getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idChild = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int idReference = result.getInt("ID_REFERENCE");
            String surname = result.getString("SURNAME");
            String name = result.getString("NAME");
            String middleName = result.getString("MIDDLE_NAME");
            String gender = result.getString("GENDER");
            String dateOfBirth = result.getString("DATE_OF_BIRTH");
            String note = result.getString("NOTE");
            String referenceSTime = result.getString("REFERENCE_S_TIME");
            String referenceETime = result.getString("REFERENCE_E_TIME");
            String pairLessons = result.getString("PAIR_LESSONS");
            return new Child(idChild, idClient, idReference, surname, name, middleName, Gender.valueOf(gender), dateOfBirth, note, referenceSTime, referenceETime, pairLessons);
        }

        return null;
    }

    @Override
    public List<Child> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<Child> children = new ArrayList<>();

        while (result.next()) {
            int idChild = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int idReference = result.getInt("ID_REFERENCE");
            String surname = result.getString("SURNAME");
            String name = result.getString("NAME");
            String middleName = result.getString("MIDDLE_NAME");
            String gender = result.getString("GENDER");
            String dateOfBirth = result.getString("DATE_OF_BIRTH");
            String note = result.getString("NOTE");
            String referenceSTime = result.getString("REFERENCE_S_TIME");
            String referenceETime = result.getString("REFERENCE_E_TIME");
            String pairLessons = result.getString("PAIR_LESSONS");
            children.add(new Child(idChild, idClient, idReference, surname, name, middleName, Gender.valueOf(gender), dateOfBirth, note, referenceSTime, referenceETime, pairLessons));
        }

        return children;
    }

    @Override
    public int save(Child data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, data.getIdClient());
        statement.setObject(2, data.getIdReference());
        statement.setString(3, data.getSurname());
        statement.setString(4, data.getName());
        statement.setString(5, data.getMiddleName());
        statement.setString(6, data.getGender().name());
        statement.setString(7, data.getDateOfBirth());
        statement.setString(8, data.getNote());
        statement.setString(9, data.getReferenceSTime());
        statement.setString(10, data.getReferenceETime());
        statement.setString(11, data.getPairLessons());
        return executeId(statement);    }

    @Override
    public boolean update(Child data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, data.getIdClient());
        statement.setObject(2, data.getIdReference());
        statement.setString(3, data.getSurname());
        statement.setString(4, data.getName());
        statement.setString(5, data.getMiddleName());
        statement.setString(6, data.getGender().name());
        statement.setString(7, data.getDateOfBirth());
        statement.setString(8, data.getNote());
        statement.setString(9, data.getReferenceSTime());
        statement.setString(10, data.getReferenceETime());
        statement.setString(11, data.getPairLessons());
        statement.setInt(12, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}
