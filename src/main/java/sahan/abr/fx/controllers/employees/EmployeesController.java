package sahan.abr.fx.controllers.employees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import sahan.abr.entities.Security;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.entities.Employee;

import java.sql.SQLException;

import static sahan.abr.Main.*;

public class EmployeesController {

    @FXML
    private TextField textFieldSearchSurname;

    @FXML
    private TableView<Employee> tableViewEmployees;

    @FXML
    private TableColumn<Employee, String> tableColumnSurnameEmployee;

    @FXML
    private TableColumn<Employee, String> tableColumnNameEmployee;

    @FXML
    private TableColumn<Employee, String> tableColumnMiddleNameEmployee;

    @FXML
    private TableColumn<Employee, String> tableColumnPositionEmployee;

    @FXML
    private TableColumn<Employee, String> tableColumnPhoneNumberEmployee;

    private EmployeesController employeesController = this;

    @FXML
    private void initialize() throws SQLException {

        observableListEmployees = FXCollections.observableArrayList();
        observableListEmployees.addAll(employeeRepository.getAll());

        tableViewEmployees.setItems(observableListEmployees);

        tableColumnSurnameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
        tableColumnNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        tableColumnMiddleNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("middleName"));
        tableColumnPositionEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        tableColumnPhoneNumberEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));

        addButtonsToTableEmployees();

        tableViewEmployees.setVisible(true);
        tableViewEmployees.setEditable(true);
    }

    @FXML
    void addEmployee(ActionEvent event) {
        Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_EMPLOYEE);
    }

    @FXML
    void clearFilterSearchEmployees(ActionEvent event) {
        textFieldSearchSurname.setText("");
        tableViewEmployees.setItems(observableListEmployees);
    }

    @FXML
    void searchEmployees(ActionEvent event) {
        String valueSurname = textFieldSearchSurname.getText();

        if (valueSurname == null || valueSurname.isEmpty()){
            tableViewEmployees.setItems(observableListEmployees);
        } else {
            ObservableList<Employee> observableListEmployeesTMP = FXCollections.observableArrayList();

            for (Employee subscription : observableListEmployees) {
                if (subscription.getSurname().equals(valueSurname)){
                    observableListEmployeesTMP.add(subscription);
                }
            }

            tableViewEmployees.setItems(observableListEmployeesTMP);
        }
    }

    public TableView<Employee> getTableViewEmployees() {
        return tableViewEmployees;
    }

    public EmployeesController getEmployeesController() {
        return employeesController;
    }

    private void addButtonsToTableEmployees() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory =
                new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
                    @Override
                    public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                        final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                            private final HBox hBox = new HBox();
                            {
                                hBox.setSpacing(10);
                                hBox.setAlignment(Pos.CENTER);

                                Button buttonDelete = new Button("Удалить");
                                buttonDelete.setPrefWidth(93);
                                buttonDelete.setAlignment(Pos.CENTER_RIGHT);
                                buttonDelete.getStyleClass().add("toggle-button-delete-left");

                                buttonDelete.setOnAction((ActionEvent event) -> {
                                    Employee data = getTableView().getItems().get(getIndex());

                                    if (data.getPosition().equals("Администратор")) {
                                        try {
                                            Security security = authorizationRep.getByIdEmployee(data.getId());

                                            if (security != null) {
                                                authorizationRep.deleteById(security.getId());
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    observableListEmployees.remove(data);
                                    try {
                                        employeeRepository.deleteById(data.getId());
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                Button buttonUpdate = new Button("Обновить");
                                buttonUpdate.setPrefWidth(103);
                                buttonUpdate.setAlignment(Pos.CENTER_RIGHT);
                                buttonUpdate.getStyleClass().add("toggle-button-update-left");

                                buttonUpdate.setOnAction((ActionEvent event) -> {
                                    Employee data = getTableView().getItems().get(getIndex());
                                    ModalUpdateEmployeeController controller = new ModalUpdateEmployeeController(data, employeesController);
                                    Navigator.getModalWindow("SRM", Navigator.MODAL_UPDATE_EMPLOYEE, controller);
                                });

                                hBox.getChildren().add(buttonDelete);
                                hBox.getChildren().add(buttonUpdate);
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(hBox);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);

        tableViewEmployees.getColumns().add(colBtn);

    }

}
