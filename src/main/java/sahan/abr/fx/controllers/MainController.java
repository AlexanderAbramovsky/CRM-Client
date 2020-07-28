package sahan.abr.fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private Button buttonTimeTable;

    @FXML
    private Button buttonSubscription;

    @FXML
    private Button buttonEmployees;

    @FXML
    private Button buttonCustomers;

    @FXML
    private Button buttonEmployeesTimeTable;

    @FXML
    private Button buttonReports;

    @FXML
    private StackPane vistaHolder;

    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

    @FXML
    void actionTimeTable(ActionEvent event) {
        Navigator.loadVista(Navigator.TIMETABLE);
        buttonTimeTable.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonReports.setStyle("-fx-border-color: none; -fx-border-width : 3px");
    }

    @FXML
    void actionSubscription(ActionEvent event) {
        Navigator.loadVista(Navigator.SUBSCRIPTION);
        buttonTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonReports.setStyle("-fx-border-color: none; -fx-border-width : 3px");
    }

    @FXML
    void actionEmployees(ActionEvent event) {
        Navigator.loadVista(Navigator.EMPLOYEE);
        buttonTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
        buttonReports.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
    }

    @FXML
    void actionCustomers(ActionEvent event) {
        Navigator.loadVista(Navigator.CUSTOMERS);
        buttonTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonReports.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
    }

    @FXML
    void actionEmployeesTimeTable(ActionEvent event) {
        Navigator.loadVista(Navigator.EMPLOYEE_TIME_TABLE);
        buttonTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonReports.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
    }

    @FXML
    void actionReports(ActionEvent event) {
        Navigator.loadVista(Navigator.REPORTS);
        buttonReports.setStyle("-fx-border-color: aliceblue; -fx-border-width : 3px");
        buttonTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonCustomers.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonSubscription.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployees.setStyle("-fx-border-color: none; -fx-border-width : 3px");
        buttonEmployeesTimeTable.setStyle("-fx-border-color: none; -fx-border-width : 3px");
    }
}