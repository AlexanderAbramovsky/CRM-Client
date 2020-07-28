package sahan.abr.repository;

import sahan.abr.entities.ActiveSubscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveSubscriptionRep implements CRUDRepository<ActiveSubscription> {

    private final static String INSERT = "INSERT INTO ACTIVE_SUBSCRIPTION (CLIENT_ID, TITLE_SUBSCRIPTION, CLASSES_SUBSCRIPTION, S_TIME_SUBSCRIPTION, E_TIME_SUBSCRIPTION, FREEZE_SUBSCRIPTION, E_TIMEFREEZE, S_TIMEFREEZE, NOTE_FREEZE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE ACTIVE_SUBSCRIPTION SET CLIENT_ID = ?, TITLE_SUBSCRIPTION = ?, CLASSES_SUBSCRIPTION = ?, S_TIME_SUBSCRIPTION = ?, E_TIME_SUBSCRIPTION = ?, FREEZE_SUBSCRIPTION = ?, E_TIMEFREEZE = ?, S_TIMEFREEZE = ?, NOTE_FREEZE = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM CHILD WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM ACTIVE_SUBSCRIPTION";
    private final static String SELECT_BY_ID = "SELECT * FROM ACTIVE_SUBSCRIPTION WHERE ID = ?";

    public static Connection connection;

    public ActiveSubscriptionRep(Connection connection) {
        this.connection = connection;
        System.out.println("Есть подключение!");
    }

    @Override
    public ActiveSubscription getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idActiveSubscription = result.getInt("ID");
            int idClient = result.getInt("CLIENT_ID");
            String title = result.getString("TITLE_SUBSCRIPTION");
            int classes = result.getInt("CLASSES_SUBSCRIPTION");
            String sTimeSub = result.getString("S_TIME_SUBSCRIPTION");
            String eTimeSub = result.getString("E_TIME_SUBSCRIPTION");
            String freeze = result.getString("FREEZE_SUBSCRIPTION");
            String eFreeze = result.getString("E_TIMEFREEZE");
            String sFreeze = result.getString("S_TIMEFREEZE");
            String noteFreeze = result.getString("NOTE_FREEZE");
            return new ActiveSubscription(idActiveSubscription, idClient, title, classes, sTimeSub, eTimeSub, freeze, eFreeze, sFreeze, noteFreeze);
        }

        return null;
    }

    @Override
    public List<ActiveSubscription> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<ActiveSubscription> children = new ArrayList<>();

        while (result.next()) {
            int idActiveSubscription = result.getInt("ID");
            int idClient = result.getInt("CLIENT_ID");
            String title = result.getString("TITLE_SUBSCRIPTION");
            int classes = result.getInt("CLASSES_SUBSCRIPTION");
            String sTimeSub = result.getString("S_TIME_SUBSCRIPTION");
            String eTimeSub = result.getString("E_TIME_SUBSCRIPTION");
            String freeze = result.getString("FREEZE_SUBSCRIPTION");
            String eFreeze = result.getString("E_TIMEFREEZE");
            String sFreeze = result.getString("S_TIMEFREEZE");
            String noteFreeze = result.getString("NOTE_FREEZE");
            children.add(new ActiveSubscription(idActiveSubscription, idClient, title, classes, sTimeSub, eTimeSub, freeze, eFreeze, sFreeze, noteFreeze));
        }

        return children;
    }

    @Override
    public int save(ActiveSubscription data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, data.getClientId());
        statement.setObject(2, data.getTitleSubscription());
        statement.setObject(3, data.getClassesSubscription());
        statement.setString(4, data.getSTimeSubscription());
        statement.setString(5, data.getETimeSubscription());
        statement.setString(6, data.getFreezeSubscription());
        statement.setString(7, data.getETimeFreeze());
        statement.setString(8, data.getSTimeFreeze());
        statement.setString(9, data.getNoteFreeze());
        return executeId(statement);
    }

    @Override
    public boolean update(ActiveSubscription data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setObject(1, data.getClientId());
        statement.setObject(2, data.getTitleSubscription());
        statement.setObject(3, data.getClassesSubscription());
        statement.setString(4, data.getSTimeSubscription());
        statement.setString(5, data.getETimeSubscription());
        statement.setString(6, data.getFreezeSubscription());
        statement.setString(7, data.getETimeFreeze());
        statement.setString(8, data.getSTimeFreeze());
        statement.setString(9, data.getNoteFreeze());
        statement.setInt(10, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }

}
