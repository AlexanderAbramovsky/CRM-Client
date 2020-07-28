package sahan.abr.fx.controllers.schedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sahan.abr.entities.*;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.fx.controllers.customer.AddClientController;
import sahan.abr.lib.LibSRM;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static sahan.abr.Main.*;

public class AddSchedule {
    @FXML
    private TextField textFieldSearch;

    @FXML
    private RadioButton radioButtonFio;

    @FXML
    private ToggleGroup search;

    @FXML
    private RadioButton radioButtonGirlPhoneNumber;

    @FXML
    private Button buttonSearch;

    @FXML
    private CheckBox checkBoxPermanentSchedule;

    @FXML
    private TableView<Client> tableViewClient;

    @FXML
    private TableColumn<Client, String> tableColumnF;

    @FXML
    private TableColumn<Client, String> tableColumnI;

    @FXML
    private TableColumn<Client, String> tableColumnO;

    @FXML
    private TableColumn<Client, String> tableColumnPhoneNumber;

    @FXML
    private HBox NewClient;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private TextField textFieldName1;

    @FXML
    private TextField textFieldName11;

    @FXML
    private Button save1;

    @FXML
    private Label mainLabel;

    boolean pairLessons;

    Calendar calendar;
    Date dateCalendar;

    String date;
    String sTime;
    String eTime;
    String poll;
    Client client;

    ObservableList<Client> clientsSearch;

    public AddSchedule(String date, String sTime, String eTime, String poll) {
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.poll = poll;
    }

    public AddSchedule(String date, String sTime, String eTime, String poll, boolean pairLessons) {
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.poll = poll;
        this.pairLessons = pairLessons;
    }

    @FXML
    private void initialize() throws SQLException {

        radioButtonFio.setSelected(true);

        clientObservableList = FXCollections.observableArrayList();

        //только парные занятия
        if (pairLessons) {
            clientObservableList.addAll(getPairLessons());
            mainLabel.setText("Добавление занятия (Только парные клиенты)");
        } else {
            clientObservableList.addAll(clientRepository.getAll());
        }

        tableViewClient.refresh();
        tableViewClient.setItems(clientObservableList);

        tableColumnF.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
        tableColumnI.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        tableColumnO.setCellValueFactory(new PropertyValueFactory<Client, String>("middleName"));
        tableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("contactPhoneNumber"));

        addButtonsToTableCustomers();
    }


    @FXML
    void save(ActionEvent event) throws SQLException {
//        client = comboBoxClients.getValue();
//        Schedule schedule = new Schedule(ScheduleRole.CLIENT, client.getId(), data, sTime, eTime, poll, null);
//        scheduleRepository.save(schedule);
//
//        // get a handle to the stage
//        Stage stage = (Stage) save.getScene().getWindow();
//        // do what you have to do
//        stage.close();
//
//        Navigator.loadVista(Navigator.TIMETABLE);
    }

    @FXML
    void clickCheck(ActionEvent event) {

    }


    @FXML
    void searchClient(ActionEvent event) {
        clientsSearch = FXCollections.observableArrayList();


        if (radioButtonFio.isSelected()) {
            for (Client c : clientObservableList) {
                if (c.getSurname().toLowerCase().contains(textFieldSearch.getText().toLowerCase())) {
                    clientsSearch.add(c);
                }
            }
        } else {
            for (Client c : clientObservableList) {
                if (c.getContactPhoneNumber().toLowerCase().contains(textFieldSearch.getText().toLowerCase())) {
                    clientsSearch.add(c);
                }
            }
        }

        tableViewClient.refresh();
        tableViewClient.setItems(clientsSearch);
    }

    @FXML
    void chanelSearchClient(ActionEvent event) throws SQLException {
        clientObservableList = FXCollections.observableArrayList();
        clientObservableList.addAll(clientRepository.getAll());
        tableViewClient.refresh();
        tableViewClient.setItems(clientObservableList);
    }

    @FXML
    void setSearch(ActionEvent event) {

    }

    private void addButtonsToTableCustomers() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory =
                new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
                    @Override
                    public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                        final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                            private final HBox hBox = new HBox();
                            {
                                hBox.setSpacing(10);
                                hBox.setAlignment(Pos.CENTER);

                                Button buttonActive = new Button("Сохранить");
                                buttonActive.setPrefWidth(110);
                                buttonActive.setAlignment(Pos.CENTER);
                                buttonActive.getStyleClass().add("toggle-button");

                                buttonActive.setOnAction((ActionEvent event) -> {
                                    Client data = getTableView().getItems().get(getIndex());

                                    try {

                                        Schedule schedule = new Schedule(ScheduleRole.CLIENT, data.getId(), date, sTime, eTime, poll, null);

                                        if (checkBoxPermanentSchedule.isSelected()) {
                                            saveAllDate(data);
                                        } else {
                                            scheduleRepository.save(schedule);
                                        }


                                        Stage stage = (Stage) buttonActive.getScene().getWindow();
                                        // do what you have to do
                                        stage.close();

                                        Navigator.loadVista(Navigator.TIMETABLE);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                hBox.getChildren().add(buttonActive);
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

    private void saveAllDate(Client client) throws SQLException {
        ActiveSubscription activeSubscription = activeSubscriptionRep.getById(client.getIdActiveSubscription());

        calendar = Calendar.getInstance();
        dateCalendar = calendar.getTime();

        //на ближайшие 25 дней
        for (int i = 0; i < 25; i++) {

            Schedule schedule = new Schedule(ScheduleRole.CLIENT, client.getId(), LibSRM.getDateStringFormat(calendar), sTime, eTime, poll, null);
            scheduleRepository.save(schedule);

            dateCalendar.setDate(dateCalendar.getDate() + 1);
            calendar.setTime(dateCalendar);
        }

    }

    private ObservableList<Client> getPairLessons() throws SQLException {
        ObservableList<Client> allClients = FXCollections.observableArrayList();
        allClients.addAll(clientRepository.getAll());

        ObservableList<Client> clientsPairLessons = FXCollections.observableArrayList();


        for (Client c : allClients) {
            Child child = childRep.getById(c.getIdChild());
            if (child.getPairLessons().equals("Y")) {
                clientsPairLessons.add(c);
            }
        }

        return clientsPairLessons;
    }
}
