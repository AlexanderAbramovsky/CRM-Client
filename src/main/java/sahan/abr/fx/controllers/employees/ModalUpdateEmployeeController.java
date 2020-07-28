package sahan.abr.fx.controllers.employees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Security;
import sahan.abr.fx.controllers.Navigator;

import java.sql.SQLException;

import static sahan.abr.Main.*;

public class ModalUpdateEmployeeController {

    @FXML
    private Label labelEmployee;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private ComboBox<String> comboBoxPosition;

    @FXML
    private HBox h1;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private HBox h2;

    @FXML
    private PasswordField password1;

    @FXML
    private HBox h3;

    @FXML
    private PasswordField password2;

    @FXML
    private Label LabelError;

    @FXML
    private Button updateButton;

    @FXML
    void actionAdministrator(ActionEvent event) {
        if (comboBoxPosition.getValue().equals("Администратор")) {
            h1.setVisible(true);
            h2.setVisible(true);
            h3.setVisible(true);
        } else {
            h1.setVisible(false);
            h2.setVisible(false);
            h3.setVisible(false);
            LabelError.setVisible(false);
        }
    }


    private Employee employee;
    private EmployeesController employeesController;
    private boolean information;

    public ModalUpdateEmployeeController(Employee employee, EmployeesController employeesController) {
        this.employee = employee;
        this.employeesController = employeesController;
    }

    public ModalUpdateEmployeeController(Employee employee, boolean information) {
        this.employee = employee;
        this.information = information;
    }

    @FXML
    private void initialize() {
        comboBoxPosition.setItems(observableListPosition);

        textFieldSurname.setText(employee.getSurname());
        textFieldName.setText(employee.getName());
        textFieldMiddleName.setText(employee.getMiddleName());
        textFieldPhoneNumber.setText(employee.getPhoneNumber());

        comboBoxPosition.setValue(employee.getPosition());

        if (comboBoxPosition.getValue().equals("Администратор")) {
            h1.setVisible(true);
            h2.setVisible(true);
            h3.setVisible(true);
            LabelError.setVisible(false);
        } else {
            h1.setVisible(false);
            h2.setVisible(false);
            h3.setVisible(false);
            LabelError.setVisible(false);
        }

        if (information) {
            updateButton.setVisible(false);
            labelEmployee.setText("Информация о клиенте");
        }
    }

    @FXML
    void updateEmployee(ActionEvent event) throws SQLException {

        employee.setSurname(textFieldSurname.getText());
        employee.setName(textFieldName.getText());
        employee.setMiddleName(textFieldMiddleName.getText());
        employee.setPhoneNumber(textFieldPhoneNumber.getText());
        employee.setPosition(comboBoxPosition.getValue());
        employeesController.getTableViewEmployees().refresh();

        employeeRepository.update(employee);

        if (comboBoxPosition.getValue().equals("Администратор")) {

            if (password1.getText().trim().equals(password2.getText().trim()) && authorizationRep.checkLogin(textFieldLogin.getText().trim()) && !textFieldLogin.getText().trim().equals("")) {

                Security security = authorizationRep.getByIdEmployee(employee.getId());

                if (security == null) {
                    authorizationRep.save(new Security(textFieldLogin.getText().trim(), password1.getText().trim(), employee.getId()));
                } else {
                    security.setLogin(textFieldLogin.getText().trim());
                    security.setPassword(password1.getText().trim());
                    authorizationRep.update(security);
                }

                Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
                Navigator.loadVista(Navigator.EMPLOYEE);
            } else {
                LabelError.setVisible(true);
            }

        } else {
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
            Navigator.loadVista(Navigator.EMPLOYEE);
        }
    }

}
