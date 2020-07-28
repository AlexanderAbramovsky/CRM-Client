package sahan.abr.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sahan.abr.entities.Subscription;

public class SubscriptionRepository implements CRUDRepository<Subscription> {

    private final static String INSERT = "INSERT INTO SUBSCRIPTION (TITLE_SUBSCRIPTION, PRICE_SUBSCRIPTION, VALIDITY, NUMBER_CLASSES) VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE  SUBSCRIPTION SET TITLE_SUBSCRIPTION = ?, PRICE_SUBSCRIPTION = ?, VALIDITY = ?, NUMBER_CLASSES = ? WHERE ID = ?";
    private final static String DELETE = "DELETE FROM SUBSCRIPTION WHERE ID = ?";
    private final static String SELECT_ALL = "SELECT * FROM SUBSCRIPTION";
    private final static String SELECT_BY_ID = "SELECT * FROM SUBSCRIPTION WHERE ID = ?";

    public static Connection connection;

    public SubscriptionRepository(Connection connection) {
        this.connection = connection;
        System.out.println("База Подключена!");
    }

    @Override
    public Subscription getById(Integer id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.isFirst()) {
            int idSubscription = result.getInt("ID");
            String titleSubscription = result.getString("TITLE_SUBSCRIPTION");
            double priceSubscription = result.getDouble("PRICE_SUBSCRIPTION");
            int validity = result.getInt("VALIDITY");
            int numberClasses = result.getInt("NUMBER_CLASSES");
            return new Subscription(idSubscription, titleSubscription, priceSubscription, validity, numberClasses);
        }

        return null;
    }


    @Override
    public List<Subscription> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = statement.executeQuery();

        List<Subscription> subscriptions = new ArrayList<>();

        while (result.next()) {
            int idSubscription = result.getInt("ID");
            String titleSubscription = result.getString("TITLE_SUBSCRIPTION");
            double priceSubscription = result.getDouble("PRICE_SUBSCRIPTION");
            int validity = result.getInt("VALIDITY");
            int numberClasses = result.getInt("NUMBER_CLASSES");
            subscriptions.add(new Subscription(idSubscription, titleSubscription, priceSubscription, validity, numberClasses));
        }

        return subscriptions;
    }

    @Override
    public int save(Subscription data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, data.getTitleSubscription());
        statement.setDouble(    2, data.getPriceSubscription());
        statement.setInt(3, data.getValidity());
        statement.setInt(4, data.getNumberClasses());
        return executeId(statement);
    }

    @Override
    public boolean update(Subscription data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, data.getTitleSubscription());
        statement.setDouble(    2, data.getPriceSubscription());
        statement.setInt(3, data.getValidity());
        statement.setInt(4, data.getNumberClasses());
        statement.setInt(5, data.getId());
        return executeDML(statement);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        return executeDML(statement);
    }
}
