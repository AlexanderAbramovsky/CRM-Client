package sahan.abr.repository;

import sahan.abr.entities.SubscriptionsReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsReportRep implements CRUDRepository<SubscriptionsReport> {

    private final static String INSERT = "INSERT INTO SUBSCRIPTIONS_REPORT (ID_CLIENT, ID_EMPLOYEE, FIO_EMPLOYEE, FIO_CLIENT, SUBSCRIPTION, DATE, MONEY, NOTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE  SUBSCRIPTIONS_REPORT SET ID_CLIENT = ?, ID_EMPLOYEE = ?, FIO_EMPLOYEE = ?, FIO_CLIENT = ?, SUBSCRIPTION = ?, DATE = ?, MONEY = ?, NOTE = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM SUBSCRIPTIONS_REPORT WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM SUBSCRIPTIONS_REPORT";
    private final static String SELECT_BY_ID = "SELECT * FROM SUBSCRIPTIONS_REPORT WHERE ID = ?";

    public static Connection connection;

    public SubscriptionsReportRep(Connection connection) {
        this.connection = connection;
        System.out.println("База Подключена!");
    }

    @Override
    public SubscriptionsReport getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idSubscriptionsReport = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            String fioEmployee = result.getString("FIO_EMPLOYEE");
            String fioClient = result.getString("FIO_CLIENT");
            String subscription = result.getString("SUBSCRIPTION");
            String date = result.getString("DATE");
            Float  money = result.getFloat("MONEY");
            String note = result.getString("NOTE");
            return new SubscriptionsReport(idSubscriptionsReport, idClient, idEmployee, fioEmployee, fioClient, subscription, date, money, note);
        }

        return null;
    }

    @Override
    public List<SubscriptionsReport> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<SubscriptionsReport> subscriptionsReports = new ArrayList<>();

        while (result.next()) {
            int idSubscriptionsReport = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            String fioEmployee = result.getString("FIO_EMPLOYEE");
            String fioClient = result.getString("FIO_CLIENT");
            String subscription = result.getString("SUBSCRIPTION");
            String date = result.getString("DATE");
            Float  money = result.getFloat("MONEY");
            String note = result.getString("NOTE");
            subscriptionsReports.add(new SubscriptionsReport(idSubscriptionsReport, idClient, idEmployee, fioEmployee, fioClient, subscription, date, money, note));
        }

        return subscriptionsReports;
    }

    @Override
    public int save(SubscriptionsReport data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, data.getIdClient());
        statement.setObject(    2, data.getIdEmployee());
        statement.setString(3, data.getFioEmployee());
        statement.setString(4, data.getFioClient());
        statement.setString(5, data.getSubscription());
        statement.setString(6, data.getDate());
        statement.setObject(7, data.getMoney());
        statement.setString(8, data.getNote());
        return executeId(statement);
    }

    @Override
    public boolean update(SubscriptionsReport data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setObject(1, data.getIdClient());
        statement.setObject(    2, data.getIdEmployee());
        statement.setString(3, data.getFioEmployee());
        statement.setString(4, data.getFioClient());
        statement.setString(5, data.getSubscription());
        statement.setString(6, data.getDate());
        statement.setObject(7, data.getMoney());
        statement.setString(8, data.getNote());
        statement.setObject(9, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}
