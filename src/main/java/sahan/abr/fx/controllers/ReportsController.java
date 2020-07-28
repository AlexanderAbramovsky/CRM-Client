package sahan.abr.fx.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sahan.abr.entities.Client;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Security;
import sahan.abr.entities.SubscriptionsReport;
import sahan.abr.fx.controllers.customer.AddClientController;
import sahan.abr.fx.controllers.employees.ModalUpdateEmployeeController;

import java.sql.SQLException;

import static sahan.abr.Main.*;

public class ReportsController {
    @FXML
    private Label labelOperation;

    @FXML
    private RadioButton radioButtonWeek111;

    @FXML
    private ToggleGroup fio;

    @FXML
    private RadioButton radioButtonWeek;

    @FXML
    private RadioButton radioButtonWeek1;

    @FXML
    private RadioButton radioButtonWeek11;

    @FXML
    private ToggleGroup time;

    @FXML
    private RadioButton radioButtonWeek12;

    @FXML
    private RadioButton radioButtonWeek13;

    @FXML
    private TextField textFieldSearchSurname;

    @FXML
    private RadioButton radioButtonWeek1111;

    @FXML
    private ToggleGroup timeReport;

    @FXML
    private RadioButton radioButtonWeek3;

    @FXML
    private RadioButton radioButtonWeek14;

    @FXML
    private TextField textFieldSearchSurname2;

    @FXML
    private TextField textFieldSearchSurname21;

    @FXML
    private TableView<SubscriptionsReport> tableViewSubscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, String> fioEmployeeSubscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, String> fioClientSubscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, String> subscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, String> dateSubscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, Float> moneySubscriptionsReport;

    @FXML
    private TableColumn<SubscriptionsReport, String> noteSubscriptionsReport;

    @FXML
    private TextField textFieldSearchSurname1;

    @FXML
    private TextField textFieldSearchSurname11;

    @FXML
    private void initialize() throws SQLException {

        observableListSubscriptionsReport = FXCollections.observableArrayList();
        observableListSubscriptionsReport.addAll(subscriptionsReportRep.getAll());

        tableViewSubscriptionsReport.refresh();
        tableViewSubscriptionsReport.setItems(observableListSubscriptionsReport);

        fioEmployeeSubscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, String>("fioEmployee"));
        fioClientSubscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, String>("fioClient"));
        subscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, String>("subscription"));
        dateSubscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, String>("date"));
        moneySubscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, Float>("money"));
        noteSubscriptionsReport.setCellValueFactory(new PropertyValueFactory<SubscriptionsReport, String>("note"));

        addButtonsToTable();
    }

    private void addButtonsToTable() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<SubscriptionsReport, Void>, TableCell<SubscriptionsReport, Void>> cellFactory =
                new Callback<TableColumn<SubscriptionsReport, Void>, TableCell<SubscriptionsReport, Void>>() {
                    @Override
                    public TableCell<SubscriptionsReport, Void> call(final TableColumn<SubscriptionsReport, Void> param) {
                        final TableCell<SubscriptionsReport, Void> cell = new TableCell<SubscriptionsReport, Void>() {

                            private final HBox hBox = new HBox();
                            {
                                hBox.setSpacing(10);
                                hBox.setAlignment(Pos.CENTER);

                                Button buttonDelete = new Button("Удалить");
                                //buttonDelete.setPrefWidth(93);
                                buttonDelete.setAlignment(Pos.CENTER_RIGHT);
                                buttonDelete.getStyleClass().add("toggle-button");
                                buttonDelete.setOnAction((ActionEvent event) -> {
                                    try {
                                        SubscriptionsReport data = getTableView().getItems().get(getIndex());

                                        subscriptionsReportRep.deleteById(data.getId());

                                        observableListSubscriptionsReport = FXCollections.observableArrayList();
                                        observableListSubscriptionsReport.addAll(subscriptionsReportRep.getAll());

                                        tableViewSubscriptionsReport.refresh();
                                        tableViewSubscriptionsReport.setItems(observableListSubscriptionsReport);

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                Button buttonEmployee = new Button("Сотрудник");
//                                buttonEmployee.setPrefWidth(103);
                                buttonEmployee.setAlignment(Pos.CENTER_RIGHT);
                                buttonEmployee.getStyleClass().add("toggle-button");

                                buttonEmployee.setOnAction((ActionEvent event) -> {
                                    try {
                                        SubscriptionsReport data = getTableView().getItems().get(getIndex());

                                        Employee employee = employeeRepository.getById(data.getIdEmployee());
                                        ModalUpdateEmployeeController controller = new ModalUpdateEmployeeController(employee, true);
                                        Navigator.getModalWindow("SRM", Navigator.MODAL_UPDATE_EMPLOYEE, controller);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                Button buttonClient = new Button("Клиент");
                                buttonClient.setAlignment(Pos.CENTER_RIGHT);
                                buttonClient.getStyleClass().add("toggle-button");

                                buttonClient.setOnAction((ActionEvent event) -> {
                                    try {
                                        SubscriptionsReport data = getTableView().getItems().get(getIndex());

                                        Client client = clientRepository.getById(data.getIdClient());
                                        AddClientController controller = new AddClientController(client, "INFORM");
                                        Navigator.getModalWindow("SRM", Navigator.ADD_CUSTOMERS, controller);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                hBox.getChildren().add(buttonEmployee);
                                hBox.getChildren().add(buttonClient);
                                hBox.getChildren().add(buttonDelete);
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

        tableViewSubscriptionsReport.getColumns().add(colBtn);

    }
}
