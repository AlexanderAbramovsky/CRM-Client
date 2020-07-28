package sahan.abr.fx.controllers.active_subscription;

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


public class AddActiveSubscription {

    @FXML
    private Button back;

    @FXML
    private TableView<Subscription> tableViewSubscriptions;

    @FXML
    private TableColumn<Subscription, String> tableColumnTitleSubscription;

    @FXML
    private TableColumn<Subscription, Double> tableColumnPriceSubscription;

    @FXML
    private TableColumn<Subscription, Integer> tableColumnValiditySubscription;

    @FXML
    private TableColumn<Subscription, Integer> tableColumnNumberClassesSubscription;

    private Client client;
    private Calendar calendar = Calendar.getInstance();

    public AddActiveSubscription(Client client) {
        this.client = client;
    }

    @FXML
    private void initialize() throws SQLException {
        observableListSubscriptions = FXCollections.observableArrayList();
        observableListSubscriptions.addAll(subscriptionRepository.getAll());
        tableViewSubscriptions.refresh();
        tableViewSubscriptions.setItems(observableListSubscriptions);

        tableColumnTitleSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, String>("titleSubscription"));
        tableColumnPriceSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Double>("priceSubscription"));
        tableColumnValiditySubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Integer>("validity"));
        tableColumnNumberClassesSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Integer>("numberClasses"));

        addButtonsToTableSubscriptions();
    }

    @FXML
    void back(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) back.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void addButtonsToTableSubscriptions() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<Subscription, Void>, TableCell<Subscription, Void>> cellFactory =
                new Callback<TableColumn<Subscription, Void>, TableCell<Subscription, Void>>() {
                    @Override
                    public TableCell<Subscription, Void> call(final TableColumn<Subscription, Void> param) {
                        final TableCell<Subscription, Void> cell = new TableCell<Subscription, Void>() {

                            private final HBox hBox = new HBox();
                            {
                                hBox.setSpacing(10);
                                hBox.setAlignment(Pos.CENTER);

                                Button buttonActive = new Button("Активировать");
                                buttonActive.setPrefWidth(130);
                                buttonActive.setAlignment(Pos.CENTER);
                                buttonActive.getStyleClass().add("toggle-button");

                                buttonActive.setOnAction((ActionEvent event) -> {
                                    Subscription data = getTableView().getItems().get(getIndex());
                                    try {

                                        if (client.getIdActiveSubscription() != null) {
                                            ActiveSubscription activeSubscription = activeSubscriptionRep.getById(client.getId());

                                            activeSubscription.setTitleSubscription(data.getTitleSubscription());
                                            activeSubscription.setClassesSubscription(activeSubscription.getClassesSubscription() + data.getNumberClasses());
                                            activeSubscription.setSTimeSubscription(LibSRM.getDateStringFormat(calendar));
                                            activeSubscription.setETimeSubscription(getEndTime(data.getValidity()));

                                            activeSubscriptionRep.update(activeSubscription);

                                            client.setIdActiveSubscription(activeSubscription.getId());
                                            clientRepository.update(client);
                                        } else {
                                            int id = activeSubscriptionRep.save(
                                                    new ActiveSubscription(
                                                            client.getId(),
                                                            data.getTitleSubscription(),
                                                            data.getNumberClasses(),
                                                            LibSRM.getDateStringFormat(calendar),
                                                            getEndTime(data.getValidity()),
                                                            "N",
                                                            null,
                                                            null,
                                                            null
                                                    ));
                                            client.setIdActiveSubscription(id);
                                            clientRepository.update(client);
                                        }

                                        Employee employee = employeeRepository.getById(SECURITY_ADMINISTRATOR.getIdEmployee());
                                        SubscriptionsReport subscriptionsReport = new SubscriptionsReport(
                                                client.getId(),
                                                employee.getId(),
                                                LibSRM.getFIO(employee),
                                                LibSRM.getFIO(client),
                                                data.getTitleSubscription(),
                                                LibSRM.getDateStringFormat(Calendar.getInstance()),
                                                (float) data.getPriceSubscription(),
                                                "Купили Абонемент"
                                        );
                                        subscriptionsReportRep.save(subscriptionsReport);

                                        Stage stage = (Stage) back.getScene().getWindow();
                                        stage.close();

                                        AddClientController controller = new AddClientController(client);
                                        Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
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

        tableViewSubscriptions.getColumns().add(colBtn);

    }

    private String getEndTime(int validity){
        Date date = calendar.getTime();
        date.setDate(date.getDate() + validity);
        calendar.setTime(date);
        return LibSRM.getDateStringFormat(calendar);
    }
}
