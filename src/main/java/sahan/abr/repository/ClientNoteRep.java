package sahan.abr.repository;

import sahan.abr.entities.ActiveSubscription;
import sahan.abr.entities.ClientNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientNoteRep implements CRUDRepository<ClientNote>{
    private final static String INSERT = "INSERT INTO CLIENT_NOTE (ID_CLIENT, NOTE, WEEK) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE CLIENT_NOTE SET ID_CLIENT = ?, NOTE = ?, WEEK = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM CLIENT_NOTE WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM CLIENT_NOTE";
    private final static String SELECT_BY_ID = "SELECT * FROM CLIENT_NOTE WHERE ID = ?";
    private final static String SELECT_BY_ID_CLIENT_AND_WEEK = "SELECT * FROM CLIENT_NOTE WHERE ID_CLIENT = ? AND WEEK = ?";
    private final static String SELECT_ALL_BY_WEEK = "SELECT * FROM CLIENT_NOTE WHERE WEEK = ?";

    public static Connection connection;

    public ClientNoteRep(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public ClientNote getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idClientNote = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            String note = result.getString("NOTE");
            String week = result.getString("WEEK");
            return new ClientNote(idClientNote, idClient, note, week);
        }

        return null;
    }

    @Override
    public List<ClientNote> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<ClientNote> children = new ArrayList<>();

        while (result.next()) {
            int idClientNote = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            String note = result.getString("NOTE");
            String week = result.getString("WEEK");
            children.add(new ClientNote(idClientNote, idClient, note, week));
        }

        return children;
    }

    public List<ClientNote> getAllByWeek(String week) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_WEEK);
        statement.setString(1, week);
        ResultSet result = statement.executeQuery();

        List<ClientNote> children = new ArrayList<>();

        while (result.next()) {
            int idClientNote = result.getInt("ID");
            int idClient = result.getInt("ID_CLIENT");
            String note = result.getString("NOTE");
            String weekClientNote = result.getString("WEEK");
            children.add(new ClientNote(idClientNote, idClient, note, weekClientNote));
        }

        return children;
    }

    public ClientNote getByIdClientAndWeek(String week, Integer idClient) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_CLIENT_AND_WEEK);
        statement.setObject(1, idClient);
        statement.setString(2, week);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            int idClientNote = result.getInt("ID");
            int clientId = result.getInt("ID_CLIENT");
            String note = result.getString("NOTE");
            String weekClientNote = result.getString("WEEK");
            return new ClientNote(idClientNote, clientId, note, weekClientNote);
        }

        return null;
    }

    @Override
    public int save(ClientNote data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, data.getIdClient());
        statement.setObject(2, data.getNote());
        statement.setObject(3, data.getWeek());
        return executeId(statement);
    }

    @Override
    public boolean update(ClientNote data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setObject(1, data.getIdClient());
        statement.setObject(2, data.getNote());
        statement.setObject(3, data.getWeek());
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
