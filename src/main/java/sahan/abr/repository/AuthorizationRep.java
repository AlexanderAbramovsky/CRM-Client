package sahan.abr.repository;

import sahan.abr.entities.Security;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationRep implements CRUDRepository<Security> {

    private final static String INSERT = "INSERT INTO AUTHORIZATION (PASSWORD, LOGIN, ID_EMPLOYEE) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE AUTHORIZATION SET PASSWORD = ?, LOGIN = ?, ID_EMPLOYEE = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM AUTHORIZATION WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM AUTHORIZATION";
    private final static String CHECK_LOGIN = "SELECT * FROM AUTHORIZATION WHERE LOGIN = ?";
    private final static String SELECT_BY_ID = "SELECT * FROM AUTHORIZATION WHERE ID = ?";
    private final static String SELECT_BY_ID_ID_EMPLOYEE = "SELECT * FROM AUTHORIZATION WHERE ID_EMPLOYEE = ?";
    private final static String SELECT_BY_PASSWORD_AND_LOGIN = "SELECT * FROM AUTHORIZATION WHERE PASSWORD = ? AND LOGIN = ?";

    public static Connection connection;

    public AuthorizationRep(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public Security getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idSecurity = result.getInt("ID");
            String password = result.getString("PASSWORD");
            String login = result.getString("LOGIN");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            return new Security(idSecurity, password, login, idEmployee);
        }

        return null;
    }

    public Security getByIdEmployee(Integer idEmployee) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_ID_EMPLOYEE);
        statement.setInt(1, idEmployee);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idSecurity = result.getInt("ID");
            String password = result.getString("PASSWORD");
            String login = result.getString("LOGIN");
            int idEmployeeSecurity = result.getInt("ID_EMPLOYEE");
            return new Security(idSecurity, password, login, idEmployeeSecurity);
        }

        return null;
    }

    public Security getByPasswordAndLogin(String password, String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_PASSWORD_AND_LOGIN);
        statement.setString(1, password);
        statement.setString(2, login);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idSecurity = result.getInt("ID");
            String passwordSecurity = result.getString("PASSWORD");
            String loginSecurity = result.getString("LOGIN");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            return new Security(idSecurity, passwordSecurity, loginSecurity, idEmployee);
        }

        return null;
    }

    @Override
    public List<Security> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<Security> contracts = new ArrayList<>();

        while (result.next()) {
            int idSecurity = result.getInt("ID");
            String password = result.getString("PASSWORD");
            String login = result.getString("LOGIN");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            contracts.add(new Security(idSecurity, password, login, idEmployee));
        }

        return contracts;
    }

    public boolean checkLogin(String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CHECK_LOGIN);
        statement.setString(1, login);
        ResultSet result = statement.executeQuery();

        List<Security> contracts = new ArrayList<>();

        while (result.next()) {
            int idSecurity = result.getInt("ID");
            String password = result.getString("PASSWORD");
            String loginSecurity = result.getString("LOGIN");
            int idEmployee = result.getInt("ID_EMPLOYEE");
            contracts.add(new Security(idSecurity, password, loginSecurity, idEmployee));
        }

        return contracts.isEmpty();
    }

    @Override
    public int save(Security data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, data.getPassword());
        statement.setString(2, data.getLogin());
        statement.setObject(3, data.getIdEmployee());
        return executeId(statement);
    }

    @Override
    public boolean update(Security data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, data.getPassword());
        statement.setString(2, data.getLogin());
        statement.setInt(3, data.getIdEmployee());
        statement.setInt(4, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}

