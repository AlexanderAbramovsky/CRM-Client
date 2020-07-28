package sahan.abr.repository;

import sahan.abr.entities.Passport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportRep implements CRUDRepository<Passport> {

    private final static String INSERT = "INSERT INTO PASSPORT (ID_CLIENT, SERIES, NUMBER, DATE, ISSUED_BY, ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE PASSPORT SET ID_CLIENT = ?, SERIES = ?, NUMBER = ?, DATE = ?, ISSUED_BY = ?, ADDRESS = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM PASSPORT WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM PASSPORT";
    private final static String SELECT_BY_ID = "SELECT * FROM PASSPORT WHERE ID = ?";

    public static Connection connection;

    public PassportRep(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public Passport getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idPassport = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int series = result.getInt("SERIES");
            int number = result.getInt("NUMBER");
            String date = result.getString("DATE");
            String issuedBy = result.getString("ISSUED_BY");
            String address = result.getString("ADDRESS");
            return new Passport(idPassport, idClient, series, number, date, issuedBy, address);
        }

        return null;
    }

    @Override
    public List<Passport> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<Passport> passports = new ArrayList<>();

        while (result.next()) {
            int idPassport = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int series = result.getInt("SERIES");
            int number = result.getInt("NUMBER");
            String date = result.getString("DATE");
            String issuedBy = result.getString("ISSUED_BY");
            String address = result.getString("ADDRESS");
            passports.add(new Passport(idPassport, idClient, series, number, date, issuedBy, address));
        }

        return passports;
    }

    @Override
    public int save(Passport data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, data.getIdClient());
        statement.setInt(2, data.getSeries());
        statement.setInt(3, data.getNumber());
        statement.setString(4, data.getDate());
        statement.setString(5, data.getIssuedBy());
        statement.setString(6, data.getAddress());
        return executeId(statement);
    }

    @Override
    public boolean update(Passport data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, data.getIdClient());
        statement.setInt(2, data.getSeries());
        statement.setInt(3, data.getNumber());
        statement.setString(4, data.getDate());
        statement.setString(5, data.getIssuedBy());
        statement.setString(6, data.getAddress());
        statement.setInt(7, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}
