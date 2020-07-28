package sahan.abr.fx.controllers.customer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sahan.abr.entities.Client;
import sahan.abr.fx.controllers.Navigator;

import java.sql.SQLException;

import static sahan.abr.Main.*;
import static sahan.abr.Main.observableListEmployees;

public class CustomerController {

    @FXML
    private TableView<Client> tableViewClient;

    @FXML
    private TableColumn<Client, String> tableColumnMiddleName;

    @FXML
    private TableColumn<Client, String> tableColumnName;

    @FXML
    private TableColumn<Client, String> tableColumnSurname;

    @FXML
    private TableColumn<Client, String> tableColumnPhoneNumber;

    @FXML
    private void initialize() throws SQLException {
        clientObservableList = FXCollections.observableArrayList();
        clientObservableList.addAll(clientRepository.getAll());

        tableViewClient.setItems(clientObservableList);

        tableColumnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        addButtonsToTableCustomers();
    }

    @FXML
    void buttonAddCustomer(ActionEvent event) {
        Navigator.loadVista(Navigator.ADD_CUSTOMERS, new AddClientController());
        //Navigator.getModalWindow("SRM",Navigator.MODAL_ADD_CUSTOMER);
    }

    private void addButtonsToTableCustomers() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory =
                new Callback<>() {
                    @Override
                    public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                        final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                            private final HBox hBox = new HBox();

                            {
                                hBox.setSpacing(10);
                                hBox.setAlignment(Pos.CENTER);

                                Button buttonDelete = new Button("Удалить");
                                buttonDelete.setPrefWidth(93);
                                buttonDelete.setAlignment(Pos.CENTER_RIGHT);
                                buttonDelete.getStyleClass().add("toggle-button-delete-left");

                                buttonDelete.setOnAction((ActionEvent event) -> {
                                    Client data = getTableView().getItems().get(getIndex());
                                    try {
                                        clientRepository.deleteById(data.getId());
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    clientObservableList.remove(data);
                                });

                                Button buttonUpdate = new Button("Обновить");
                                buttonUpdate.setPrefWidth(103);
                                buttonUpdate.setAlignment(Pos.CENTER_RIGHT);
                                buttonUpdate.getStyleClass().add("toggle-button-update-left");

                                buttonUpdate.setOnAction((ActionEvent event) -> {
                                    Client data = getTableView().getItems().get(getIndex());
                                    AddClientController controller = new AddClientController(data);
                                    Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
                                });

                                Button buttonAddItionalInformation = new Button("Доп. инф.");
                                buttonAddItionalInformation.setPrefWidth(110);
                                buttonAddItionalInformation.setAlignment(Pos.CENTER_RIGHT);
                                buttonAddItionalInformation.getStyleClass().add("toggle-button-information-left");

                                buttonAddItionalInformation.setOnAction((ActionEvent event) -> {
                                    Client data = getTableView().getItems().get(getIndex());
                                    AddClientController controller = new AddClientController(data, true, true);
                                    Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
                                });

                                hBox.getChildren().add(buttonAddItionalInformation);
                                hBox.getChildren().add(buttonUpdate);
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

        tableViewClient.getColumns().add(colBtn);

    }
}
