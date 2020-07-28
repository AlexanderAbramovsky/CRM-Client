package sahan.abr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sahan.abr.fx.controllers.MainController;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.entities.*;
import sahan.abr.lib.LibSRM;
import sahan.abr.repository.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;   

public class Main extends Application {

    private final static String FOR_NAME = "org.sqlite.JDBC";
    private final static String URL = "jdbc:sqlite:testDB.s3db";

    public static SubscriptionRepository subscriptionRepository;
    public static EmployeeRepository employeeRepository;
    public static ScheduleRepository scheduleRepository;
    public static PassportRep passportRep;
    public static ContractRepository contractRepository;
    public static ClientRepository clientRepository;
    public static ChildRep childRep;
    public static ActiveSubscriptionRep activeSubscriptionRep;
    public static AuthorizationRep authorizationRep;
    public static ClientNoteRep clientNoteRep;
    public static SubscriptionsReportRep subscriptionsReportRep;


    public static ObservableList<String> observableListPosition = FXCollections.observableArrayList();
    public static ObservableList<String> observableListPoll = FXCollections.observableArrayList();
    public static ObservableList<String> observableListTime = FXCollections.observableArrayList();

    public static ObservableList<Employee> observableListEmployees = FXCollections.observableArrayList();
    public static ObservableList<Subscription> observableListSubscriptions = FXCollections.observableArrayList();
    public static ObservableList<Client> clientObservableList = FXCollections.observableArrayList();
    public static ObservableList<ClientNote> clientNoteObservableList = FXCollections.observableArrayList();
    public static ObservableList<ActiveSubscription> observableListActiveSubscriptions = FXCollections.observableArrayList();
    public static ObservableList<SubscriptionsReport> observableListSubscriptionsReport  = FXCollections.observableArrayList();


    public static Security SECURITY_ADMINISTRATOR;

    public static Stage MAIN_STAGE;

    public static String TIME_TABLE_DATE;

    @Override
    public void start(Stage stage) throws Exception{

        observableListPoll.add("Большой");
        observableListPoll.add("Средний");
        observableListPoll.add("Маленький");

        observableListTime.addAll("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00");
        observableListTime.addAll("12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30");
        observableListTime.addAll("19:00", "19:30", "20:00");

        observableListPosition.add("Тренер");
        observableListPosition.add("Администратор");

        //clientObservableList.add(customer);

        stage.setTitle("SRM");
        stage.setScene(createScene(loadAuthorizationPane()));
        MAIN_STAGE = stage;

        stage.setResizable(false);

//        stage.setMaximized(true);

        stage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
                getClass().getResourceAsStream(Navigator.MAIN)
        );

        MainController mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.loadVista(Navigator.TIMETABLE);

        return mainPane;
    }

    private Pane loadAuthorizationPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
                getClass().getResourceAsStream(Navigator.AUTHORIZATION)
        );

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add("./css/bootstrap3.css");
        scene.getStylesheets().add("./css/buttons.css");
        scene.getStylesheets().add("./css/titledPane.css");
        return scene;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName(FOR_NAME);
        Connection connection = DriverManager.getConnection(URL);

        subscriptionRepository = new SubscriptionRepository(connection);
        employeeRepository = new EmployeeRepository(connection);
        scheduleRepository = new ScheduleRepository(connection);
        passportRep = new PassportRep(connection);
        contractRepository = new ContractRepository(connection);
        clientRepository = new ClientRepository(connection);
        childRep = new ChildRep(connection);
        activeSubscriptionRep = new ActiveSubscriptionRep(connection);
        authorizationRep = new AuthorizationRep(connection);
        clientNoteRep = new ClientNoteRep(connection);
        subscriptionsReportRep = new SubscriptionsReportRep(connection);

        launch(args);
    }
}
