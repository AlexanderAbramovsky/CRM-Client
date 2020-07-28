package sahan.abr.fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sahan.abr.fx.controllers.Users.AddUserTest;
import sahan.abr.fx.controllers.employees.EmployeesController;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Parent;

import java.util.ArrayList;

public class SRM {

    private EmployeesController employees;

    // Объекты вкладки сотрудников
    @FXML
    private TextField fioField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField searchEmployeesField;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, Boolean> deleteColumn;
    @FXML
    private TableColumn<Employee, String> fioColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;
    @FXML
    private Button removeEmployeeButton;



    // Объекты вкладки клиенты
    @FXML
    private VBox mainVBoxUser;

    @FXML
    void addUserTest(ActionEvent event) {

        ArrayList<Parent> arrayList = new ArrayList<>();

        //connectionParent.getAllParents(arrayList);
        for (Parent parent : arrayList) {

            mainVBoxUser.getChildren().add(AddUserTest.getUser(parent));

        }

    }

    @FXML
    private void initialize() {

      //  employees = new EmployeesController(employeesTable, deleteColumn,
        //        fioColumn, positionColumn, phoneNumberColumn, removeEmployeeButton);
    }

    // Слушатели для управления вкладки сотрудники
    @FXML
    void deleteEmployee(ActionEvent event) {
        //employees.deleteEmployees();
    }

    @FXML
    void addEmployee(ActionEvent event) {
        //employees.addEmployee(fioField.getText(), positionField.getText(), phoneNumberField.getText());
        //employees.resetEmployees();
    }

    @FXML
    void resetEmployees(ActionEvent event) {
       //employees.resetEmployees();
       searchEmployeesField.setText("");
    }

    @FXML
    void searchEmployees(ActionEvent event) {
       // employees.searchEmployees(searchEmployeesField.getText());
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        //employees.removeEmployees();
    }

}
