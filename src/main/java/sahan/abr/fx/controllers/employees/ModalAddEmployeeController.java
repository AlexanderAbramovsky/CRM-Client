package sahan.abr.fx.controllers.employees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Security;
import sahan.abr.fx.controllers.Navigator;

import java.sql.SQLException;

import static sahan.abr.Main.*;

public class ModalAddEmployeeController {

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private Button saveButton;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private ComboBox<String> comboBoxPosition;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Label LabelError;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;


    @FXML
    private HBox h1;

    @FXML
    private HBox h2;

    @FXML
    private HBox h3;

    Employee employee;

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

    @FXML
    private void initialize() {
        h1.setVisible(false);
        h2.setVisible(false);
        h3.setVisible(false);
        LabelError.setVisible(false);

        comboBoxPosition.setItems(observableListPosition);
    }

    @FXML
    void saveEmployee(ActionEvent event) throws SQLException {

        if (employee == null){
            employee = new Employee(textFieldSurname.getText(), textFieldName.getText(), textFieldMiddleName.getText(),
                    comboBoxPosition.getValue(), textFieldPhoneNumber.getText());

            employee.setId(employeeRepository.save(employee));
        } else {
            employeeRepository.update(employee);
        }

        if (comboBoxPosition.getValue().equals("Администратор")) {

            if (password1.getText().trim().equals(password2.getText().trim()) && authorizationRep.checkLogin(textFieldLogin.getText().trim()) && !textFieldLogin.getText().trim().equals("")) {
                authorizationRep.save(new Security(textFieldLogin.getText().trim(), password1.getText().trim(), employee.getId()));

                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
                Navigator.loadVista(Navigator.EMPLOYEE);
            } else {
                LabelError.setVisible(true);
            }
        } else {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Navigator.loadVista(Navigator.EMPLOYEE);
        }
    }

}
